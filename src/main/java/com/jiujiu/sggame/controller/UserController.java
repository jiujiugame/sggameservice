package com.jiujiu.sggame.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jiujiu.sggame.entity.User;
import com.jiujiu.sggame.service.UserService;
import com.jiujiu.sggame.utils.GsonUtil;
import com.jiujiu.sggame.utils.HttpClientUtil;
import com.jiujiu.sggame.utils.SnsJsonUtil;

/**
 * UserController
 * 
 * @author 00fly
 * @version [版本号, 2018-09-11]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@RequestMapping("user")
public class UserController
{
    @Autowired
    private UserService userService;
    
    private String requestUrl = "https://jiujiuapp.cn/app/api/userinfo";
    
    @ResponseBody
    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    public Map<String, Object> view(@RequestParam String token){
    
    	if (token == null || token.length() == 0) {
			return SnsJsonUtil.resultError(new HashMap<String, Object>(), "token 为空!", "");
		}
    	
    	String result = HttpClientUtil.doHttpPost(requestUrl, token, null);
    	if (result == null || result.length() == 0) {
    		return SnsJsonUtil.resultError(new HashMap<String, Object>(), "验证无效!", "");
		}
    	
    	Map<String, Object> params = GsonUtil.getContentMap(result);
    	if (params.containsKey("status")) {
    		String status = ((Double)params.get("status")).intValue()+"";
			if (status.equals("-1")) {
				String message = params.get("message")+"";
				return SnsJsonUtil.resultError(new HashMap<String, Object>(), message, "");
			}else if(status.equals("1")){
				Map<String, Object> data = (Map<String, Object>) params.get("data");
				if(data != null) {
					Map<String, Object> account = (Map<String, Object>) data.get("account");
					if (account != null) {
						String uid = (String) account.get("uid");
						if (uid == null) {
							return SnsJsonUtil.resultError(new HashMap<String, Object>(), "token与uid不一致", "");
						}else {
							User user = userService.queryById(uid);
							if (user == null) {
								user = new User();
								user.setAccountId(uid);
								user.setToken(token);
								user.setTokenType(token);
								int res = userService.insert(user);
								if (res > 0) {
									return SnsJsonUtil.result(new HashMap<String, Object>(), "登录成功", uid);
								}
							}else {
								user.setToken(token);
								user.setTokenType(token);
								int res = userService.update(user);
								if (res > 0) {
									return SnsJsonUtil.result(new HashMap<String, Object>(), "登录成功", uid);
								}
							}
						}
					}
				}
			}
		}
    	return SnsJsonUtil.resultError(new HashMap<String, Object>(), "登录失败", "");
    }
   
}