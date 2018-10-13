//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hand.hap.adaptor.impl;

import com.google.common.base.Throwables;
import com.hand.hap.account.dto.User;
import com.hand.hap.account.exception.RoleException;
import com.hand.hap.account.exception.UserException;
import com.hand.hap.account.service.IRole;
import com.hand.hap.account.service.IRoleService;
import com.hand.hap.account.service.IUserService;
import com.hand.hap.adaptor.ILoginAdaptor;
import com.hand.hap.core.IRequest;
import com.hand.hap.core.components.CaptchaConfig;
import com.hand.hap.core.components.SysConfigManager;
import com.hand.hap.core.impl.RequestHelper;
import com.hand.hap.core.util.TimeZoneUtil;
import com.hand.hap.security.IUserSecurityStrategy;
import com.hand.hap.security.TokenUtils;
import com.hand.hap.security.captcha.ICaptchaManager;
import com.hand.hap.security.service.impl.UserSecurityStrategyManager;
import com.hand.hap.system.dto.ResponseData;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hand.hap.system.service.IProfileService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.HtmlUtils;
import org.springframework.web.util.WebUtils;

public class DefaultLoginAdaptor implements ILoginAdaptor {
    private static final boolean VALIDATE_CAPTCHA = true;
    private static final String KEY_VERIFICODE = "verifiCode";
    private static final String VIEW_INDEX = "/";
    private static final String VIEW_LOGIN = "/login";
    private static final String VIEW_ROLE_SELECT = "/role";
    @Autowired
    private ICaptchaManager captchaManager;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    @Qualifier("roleServiceImpl")
    private IRoleService roleService;
    @Autowired
    private IUserService userService;
    @Autowired
    private CaptchaConfig captchaConfig;
    @Autowired
    private SysConfigManager sysConfigManager;
    @Autowired
    UserSecurityStrategyManager userSecurityStrategyManager;

    public DefaultLoginAdaptor() {
    }

    public ModelAndView doLogin(User user, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView view = new ModelAndView();
        Locale locale = RequestContextUtils.getLocale(request);
        view.setViewName(this.getLoginView(request));

        try {
            this.beforeLogin(view, user, request, response);
            this.checkCaptcha(view, user, request, response);
            user = this.userService.login(user);
            HttpSession session = request.getSession(true);
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("userName", user.getUserName());
            session.setAttribute("locale", locale.toString());
            this.setTimeZoneFromPreference(session, user.getUserId());
            this.generateSecurityKey(session);
            this.afterLogin(view, user, request, response);
        } catch (UserException var7) {
            view.addObject("msg", this.messageSource.getMessage(var7.getCode(), var7.getParameters(), locale));
            view.addObject("code", var7.getCode());
            this.processLoginException(view, user, var7, request, response);
        }

        return view;
    }

    private void setTimeZoneFromPreference(HttpSession session, Long accountId) {
        String tz = "GMT+0800";
        if (StringUtils.isBlank(tz)) {
            tz = TimeZoneUtil.toGMTFormat(TimeZone.getDefault());
        }

        session.setAttribute("timeZone", tz);
    }

    private String generateSecurityKey(HttpSession session) {
        return TokenUtils.setSecurityKey(session);
    }

    protected void beforeLogin(ModelAndView view, User account, HttpServletRequest request, HttpServletResponse response) throws UserException {
    }

    protected void processLoginException(ModelAndView view, User account, UserException e, HttpServletRequest request, HttpServletResponse response) {
    }

    private void checkCaptcha(ModelAndView view, User user, HttpServletRequest request, HttpServletResponse response) throws UserException {
        Cookie cookie = WebUtils.getCookie(request, this.captchaManager.getCaptchaKeyName());
        String captchaCode = request.getParameter("verifiCode");
        if (cookie == null || StringUtils.isEmpty(captchaCode) || !this.captchaManager.checkCaptcha(cookie.getValue(), captchaCode)) {
            throw new UserException("error.login.verification_code_error", "error.login.verification_code_error", (Object[]) null);
        }
    }

    protected void afterLogin(ModelAndView view, User user, HttpServletRequest request, HttpServletResponse response) throws UserException {
        view.setViewName("redirect:" + this.getRoleView(request));
        Cookie cookie = new Cookie("userName", user.getUserName());
        cookie.setPath(StringUtils.defaultIfEmpty(request.getContextPath(), "/"));
        cookie.setMaxAge(-1);
        response.addCookie(cookie);
    }

    public ModelAndView doSelectRole(IRole role, HttpServletRequest request, HttpServletResponse response) throws RoleException {
        ModelAndView result = new ModelAndView();
        HttpSession session = request.getSession(false);
        if (session != null && role != null && role.getRoleId() != null) {
            Long userId = (Long) session.getAttribute("userId");
            this.roleService.checkUserRoleExists(userId, role.getRoleId());
            if (!this.sysConfigManager.getRoleMergeFlag()) {
                Long[] ids = new Long[]{role.getRoleId()};
                session.setAttribute("roleIds", ids);
            }

            session.setAttribute("roleId", role.getRoleId());
            result.setViewName("redirect:" + this.getIndexView(request));
        } else {
            result.setViewName("redirect:" + this.getLoginView(request));
        }

        return result;
    }

    protected String getIndexView(HttpServletRequest request) {
        return "/";
    }

    protected String getLoginView(HttpServletRequest request) {
        return "/login";
    }

    protected String getRoleView(HttpServletRequest request) {
        return "/role";
    }

    public IUserService getUserService() {
        return this.userService;
    }

    @Autowired
    private IProfileService profileService;
    public ModelAndView indexView(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        ModelAndView mav = this.indexModelAndView(request, response);
        if (session != null) {
            String userName = (String) session.getAttribute("userName");
            Long userId = (Long) session.getAttribute("userId");
            if (userName == null) {
                return new ModelAndView("redirect:" + this.getLoginView(request));
            }

            if (session.getAttribute("login_change_index") != null) {
                User user = this.userService.selectByUserName(userName);
                List<IUserSecurityStrategy> userSecurityStrategies = this.userSecurityStrategyManager.getUserSecurityStrategyList();
                Iterator var9 = userSecurityStrategies.iterator();

                while (var9.hasNext()) {
                    IUserSecurityStrategy userSecurityStrategy = (IUserSecurityStrategy) var9.next();
                    ModelAndView mv = userSecurityStrategy.loginVerifyStrategy(user, request);
                    if (mv != null) {
                        return mv;
                    }
                }

                session.removeAttribute("login_change_index");
            }

            if (!this.sysConfigManager.getRoleMergeFlag()) {
                Long roleId = (Long) session.getAttribute("roleId");
                if (roleId == null) {
                    return new ModelAndView("redirect:" + this.getRoleView(request));
                }

                User user = new User();
                user.setUserId(userId);
                user.setUserName(userName);
                List<IRole> roles = this.roleService.selectActiveRolesByUser(RequestHelper.createServiceRequest(request), user);
                mav.addObject("SYS_USER_ROLES", roles);
                mav.addObject("CURRENT_USER_ROLE", roleId);
            }
        }
        mav.addObject("SYS_TITLE", HtmlUtils.htmlEscape(this.sysConfigManager.getSysTitle()));

        IRequest requestCtx = RequestHelper.createServiceRequest(request);
        String color = profileService.getProfileValue(requestCtx, "XXCT_HEADER_COLOR");
        if(color==null||"".equals(color)){
            color="#3B3F51";
        }
        mav.addObject("HEADER_COLOR", color);

        return mav;
    }

    public ModelAndView indexModelAndView(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("index");
    }

    public ModelAndView loginView(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView view = new ModelAndView(this.getLoginView(request));
        Cookie cookie = WebUtils.getCookie(request, "loginKey");
        if (this.captchaConfig.getWrongTimes().intValue() > 0 && cookie == null) {
            String uuid = UUID.randomUUID().toString();
            cookie = new Cookie("loginKey", uuid);
            cookie.setPath(StringUtils.defaultIfEmpty(request.getContextPath(), "/"));
            cookie.setMaxAge(this.captchaConfig.getExpire().intValue());
            cookie.setHttpOnly(true);
            if (SysConfigManager.useHttps) {
                cookie.setSecure(true);
            }

            response.addCookie(cookie);
            this.captchaConfig.updateLoginFailureInfo(cookie);
        }

        view.addObject("ENABLE_CAPTCHA", Boolean.valueOf(this.captchaConfig.isEnableCaptcha(cookie)));
        view.addObject("SYS_TITLE", HtmlUtils.htmlEscape(this.sysConfigManager.getSysTitle()));
        Boolean error = (Boolean) request.getAttribute("error");
        Throwable exception = (Exception) request.getAttribute("exception");
        String code = "error.login.name_password_not_match";
        if (exception != null && !(exception instanceof BadCredentialsException)) {
            Throwable exception2 = Throwables.getRootCause(exception);
            code = exception.getMessage();
        }

        if (error != null && error.booleanValue()) {
            Locale locale = RequestContextUtils.getLocale(request);
            String msg = this.messageSource.getMessage(code, (Object[]) null, locale);
            view.addObject("msg", msg);
        }

        return view;
    }

    public ModelAndView roleView(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView(this.getRoleView(request));
        HttpSession session = request.getSession(false);
        mv.addObject("SYS_TITLE", HtmlUtils.htmlEscape(this.sysConfigManager.getSysTitle()));
        if (session != null) {
            Long userId = (Long) session.getAttribute("userId");
            if (userId != null) {
                User user = new User();
                user.setUserId(userId);
                user.setUserName((String) session.getAttribute("userName"));
                session.setAttribute("userId", userId);
                this.addCookie("userId", userId.toString(), request, response);
                List<IRole> roles = this.roleService.selectActiveRolesByUser(RequestHelper.createServiceRequest(request), user);
                mv.addObject("roles", roles);
            }
        }

        return mv;
    }

    public ModelAndView casLoginFailure(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView view = new ModelAndView("cas_login_failure");
        Throwable exception = (Exception) request.getAttribute("exception");
        String code = "error.login.name_password_not_match";
        if (exception != null) {
            Throwable exception2 = Throwables.getRootCause(exception);
            code = exception.getMessage();
        }

        Locale locale = RequestContextUtils.getLocale(request);
        String errorMessage = this.messageSource.getMessage(code, (Object[]) null, locale);
        view.addObject("errorMessage", errorMessage);
        return view;
    }

    protected void addCookie(String cookieName, String cookieValue, HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setPath(StringUtils.defaultIfEmpty(request.getContextPath(), "/"));
        cookie.setMaxAge(-1);
        response.addCookie(cookie);
    }

    public ResponseData sessionExpiredLogin(User account, HttpServletRequest request, HttpServletResponse response) throws RoleException {
        ResponseData data = new ResponseData();
        ModelAndView view = this.doLogin(account, request, response);
        ModelMap mm = view.getModelMap();
        if (mm.containsAttribute("code")) {
            data.setSuccess(false);
            data.setCode((String) mm.get("code"));
            data.setMessage((String) mm.get("msg"));
        } else {
            Object userIdObj = request.getParameter("userId");
            Object roleIdObj = request.getParameter("roleId");
            if (userIdObj != null && roleIdObj != null) {
                Long userId = Long.valueOf(userIdObj.toString());
                Long roleId = Long.valueOf(roleIdObj.toString());
                this.roleService.checkUserRoleExists(userId, roleId);
                HttpSession session = request.getSession();
                session.setAttribute("userId", userId);
                session.setAttribute("roleId", roleId);
            }
        }

        return data;
    }
}
