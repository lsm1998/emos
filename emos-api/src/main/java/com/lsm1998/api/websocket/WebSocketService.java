package com.lsm1998.api.websocket;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@ServerEndpoint(value = "/socket")
@Component
public class WebSocketService
{
    public static ConcurrentHashMap<String, Session> sessionMap = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session)
    {
    }

    @OnClose
    public void onClose(Session session)
    {
        Map<String, Object> map = session.getUserProperties();
        if (map.containsKey("userId"))
        {
            Object value = map.get("userId");
            String userId = (String) value;
            sessionMap.remove(userId);
        }
    }

    @OnMessage
    public void onMessage(String message, Session session)
    {
//        JSONObject json = JSONUtil.parseObj(message);
//        String opt = json.getStr("opt");
//        if ("ping".equals(opt))
//        {
//            return;
//        }
//        String token = json.getStr("token");
//        String userId = StpUtil.stpLogic.getLoginIdByToken(token).toString();
//        Map<String, Object> map = session.getUserProperties();
//        if (!map.containsKey("userId"))
//        {
//            map.put("userId", userId);
//        }
//        if (sessionMap.containsKey(userId))
//        {
//            sessionMap.replace(userId, session);
//        } else
//        {
//            sessionMap.put(userId, session);
//        }
//        sendInfo("ok", userId);
    }

    @OnError
    public void onError(Session session, Throwable error)
    {
        log.error("发生错误", error);
    }

    public static void sendInfo(String message, String userId)
    {
        if (Strings.isNotBlank(userId) && sessionMap.containsKey(userId))
        {
            Session session = sessionMap.get(userId);
            sendMessage(message, session);
        }
    }

    private static void sendMessage(String message, Session session)
    {
        try
        {
            session.getBasicRemote().sendText(message);
        } catch (Exception e)
        {
            log.error("执行异常", e);
        }
    }
}
