//package com.zhang.practice.thread.sentinel;
//
//import java.nio.charset.Charset;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.CyclicBarrier;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author : zzh
// * create at:  2020/12/9
// * @description:
// */
//public class Run {
//
//    public static CloseableHttpAsyncClient httpclient;
//
//    public static String URL = "http://192.168.10.130:8080/metric/saveMetric.json?app=ld-web-zhuying";
//
//    public static String BASE_PARAM = "timestamp|GET:/rent/test/{id}|100|122|100|0|1|0|0|1\n" +
//            "timestamp|__total_inbound_traffic__|100|122|100|0|1|0|0|0";
//
//    public static int THRESHOLD = 400;
//
//    public static CyclicBarrier cyclicBarrier = new CyclicBarrier(THRESHOLD, () -> {
//        //System.out.println("==finish==");
//    });
//
//    public static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
//
//    public static void main(String[] args) throws IOReactorException {
//
//        init();
//        scheduledExecutorService.scheduleAtFixedRate(() -> {
//            for (int i = 0; i < 200; i++) {
//                new Task().start();
//            }
//        }, 0, 1, TimeUnit.SECONDS);
//
//    }
//
//    public static void init() throws IOReactorException {
//        RequestConfig requestConfig = RequestConfig.custom()
//                .setSocketTimeout(30000)
//                .setConnectTimeout(30000)
//                .build();
//
//        // 配置io线程
//        IOReactorConfig ioConfig = IOReactorConfig.custom()
//                .setConnectTimeout(3000)
//                .setSoTimeout(3000)
//                .setIoThreadCount(1).build();
//
//        ConnectingIOReactor ioReactor = new DefaultConnectingIOReactor(ioConfig);
//        PoolingNHttpClientConnectionManager connManager =
//                new PoolingNHttpClientConnectionManager(ioReactor);
//
//        connManager.setMaxTotal(500);
//        connManager.setDefaultMaxPerRoute(500);
//
//        httpclient = HttpAsyncClients.custom()
//                .setRedirectStrategy(new DefaultRedirectStrategy() {
//                    @Override
//                    protected boolean isRedirectable(final String method) {
//                        return false;
//                    }
//                })
//                .setConnectionManager(connManager)
//                //.setDefaultRequestConfig(requestConfig)
//                .build();
//
//        httpclient.start();
//    }
//
//    static class Task extends Thread {
//
//        @Override
//        public void run() {
//
//            final HttpPost httpPost = new HttpPost(URL);
//            httpPost.setHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
//            // 声明存放参数的List集合
//            List<NameValuePair> params = new ArrayList<NameValuePair>();
//
//            String metric = BASE_PARAM.replaceAll("timestamp", System.currentTimeMillis() + "");
//            params.add(new BasicNameValuePair("metric", metric));
//            // 创建form表单对象
//            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, Charset.forName(SentinelConfig.charset()));
//
//            // 把表单对象设置到httpPost中
//            httpPost.setEntity(formEntity);
//
//            try {
//                cyclicBarrier.await();
//                httpclient.execute(httpPost, new FutureCallback<HttpResponse>() {
//                    @Override
//                    public void completed(HttpResponse httpResponse) {
//
//                    }
//
//                    @Override
//                    public void failed(Exception e) {
//
//                    }
//
//                    @Override
//                    public void cancelled() {
//
//                    }
//                });
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
