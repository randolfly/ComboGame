// /* File Info
// * Author: Randolf
// * CreateTime: 2019/12/24 下午3:00:36
// * LastEditor: Randolf
// * ModifyTime: 2019/12/24 下午3:00:46
// * Description: 观察者线程类
// */
// package combogame.model;

// import java.util.Observable;
// import java.util.Observer;

// import combogame.MainApp;
// import combogame.util.Monitor;

// public class ThreadObserver implements Observer {
// private MainApp mainApp;

// public ThreadObserver(MainApp mainApp) {
// this.mainApp = mainApp;
// }

// @Override
// public void update(Observable o, Object arg) {
// // TODO Auto-generated method stub
// System.out.println("重启线程");
// mainApp.monitor = new Monitor(this.mainApp);
// mainApp.threadMonitor = new ThreadObserver(this.mainApp);

// mainApp.monitor.addObserver(mainApp.threadMonitor);
// mainApp.monitor.run();
// }
// }