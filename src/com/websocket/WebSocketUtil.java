package com.websocket;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

//該註解用來指定一個URI，客戶端可以通過這個URI來連接到WebSocket。類似Servlet的註解mapping。無需在web.xml中配置。
@ServerEndpoint("/websocket")
public class WebSocketUtil {
   
  //concurrent包的線程安全Set，用來存放每個客戶端對應的WebSocketUtil對象。若要實現服務端與單一客戶端通信的話，可以使用Map來存放，其中Key可以為用戶標識
  private static CopyOnWriteArraySet<WebSocketUtil> webSocketSet = new CopyOnWriteArraySet<WebSocketUtil>();
  private static Map<String,WebSocketUtil> map = new ConcurrentHashMap();
   
  //與某個客戶端的連接會話，需要通過它來給客戶端發送數據
  private Session session;
   
  /**
   * 連接建立成功調用的方法
   * @param session 可選的參數。 session為與某個客戶端的連接會話，需要通過它來給客戶端發送數據
   */
  @OnOpen
  public void onOpen(Session session){
      this.session = session;
      webSocketSet.add(this); //加入set中
      System.out.println("有新連接加入！");
  }
   
  /**
   * 連接關閉調用的方法
   */
  @OnClose
  public void onClose(){
      webSocketSet.remove(this); //從set中刪除
      System.out.println("有一連接關閉！" );
  }
   
  /**
   * 收到客戶端消息後調用的方法
   * @param message 客戶端發送過來的消息
   * @param session 可選的參數
   */
  @OnMessage
  public String onMessage(String message, Session session) {
      System.out.println("來自客戶端的消息:" + message);
      return "送回去給您："+message;
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

/**
   * 群發消息
   * @param message
   */
public static void send(String message){
      for(WebSocketUtil connect: webSocketSet){
          try {
              connect.sendMessage(message);
          } catch (IOException e) {
              e.printStackTrace();
              continue;
          }
      }
}

}