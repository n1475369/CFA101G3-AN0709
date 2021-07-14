package com.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.member.model.MemVO;




//value = "/webSocketMessage"，對應的url-pattern(無需至web.xml配置)
//configurator = GetHttpSessionConfigurator.class)，通過這個類來獲取HttpSession
@ServerEndpoint(value = "/webSocketMessage", configurator = GetHttpSessionConfigurator.class)
public class WebSocketMessage {
   
  //用來存放每個客戶端對應的WebSocketMessage物件(ConcurrentHashMap解決同步問題)
  private static Map<String,WebSocketMessage> map = new ConcurrentHashMap();
  //與某個客戶端的連接會話，需要通過它來給客戶端發送數據
  private Session session;
  //獲取servelt的HttpSession用來取得用戶資訊
  private HttpSession httpsession;
  //利用jackson來轉換json格式物件
  ObjectMapper objectMapper = new ObjectMapper();

   
  /**
   * 連接建立成功調用的方法
   * @param session 可選的參數。 session為與某個客戶端的連接會話，需要通過它來給客戶端發送數據
   * @param config 用來獲取Httpsession判斷是否是登入狀態
   */
  @OnOpen
  public void onOpen(Session session,EndpointConfig config){
      this.session = session;
      this.httpsession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
      MemVO user = (MemVO)httpsession.getAttribute("user");
      map.put(user.getMem_username(), this); //加入map
      System.out.println("有新連接加入！");
      
      //傳送上線會員名稱
      try {
		Set<String> keySet = map.keySet();
		Map messMap = new HashMap();
		messMap.put("isSystem", true);
		messMap.put("username", keySet);
		for (String username : keySet) {
			WebSocketMessage webSocketMessage = map.get(username);
			String writeValueAsString = objectMapper.writeValueAsString(messMap);
			webSocketMessage.sendMessage(writeValueAsString);
		}
	  } catch (Exception e) {
		e.printStackTrace();
	  }
  }
   
  //連接關閉調用的方法，移除map對應的物件
  @OnClose
  public void onClose(){
	  MemVO user = (MemVO)httpsession.getAttribute("user");
      map.remove(user.getMem_username()); //從map中刪除
      System.out.println("有一連接關閉！" );
  }
   
  /**
   * 收到客戶端消息後調用的方法
   * @param message 客戶端發送過來的消息
   * @param session 可選的參數
   */
  @OnMessage
  public void onMessage(String data, Session session) {
	  System.out.println("來自客戶端的消息:");
      MemVO user = (MemVO)httpsession.getAttribute("user");
      try {
		MessageBase messageBase = objectMapper.readValue(data, MessageBase.class);
		String fromName = messageBase.getFromName();
		String toName = messageBase.getToName();
		String message = messageBase.getMessage();
		Map messMap = new HashMap();
		messMap.put("isSystem", false);
		messMap.put("fromName", fromName);
		messMap.put("toName", toName);
		messMap.put("message", message);
		String writeValueAsString = objectMapper.writeValueAsString(messMap);
		System.out.println(writeValueAsString);
		map.get(toName).sendMessage(writeValueAsString);
	  } catch (Exception e) {
		e.printStackTrace();
	  }
  }
   
  /**
   * 發生錯誤時調用
   * @param session
   * @param error
   */
  @OnError
  public void onError(Session session, Throwable error){
      System.out.println("發生錯誤");
      error.printStackTrace();
  }
   
  /**
   * 這個方法與上面幾個方法不一樣。沒有用註解，是根據自己需要添加的方法。
   * @param message
   * @throws IOException
   */
  public void sendMessage(String message) throws IOException{
      this.session.getBasicRemote().sendText(message);
      //this.session.getAsyncRemote().sendText(message);
  }

}