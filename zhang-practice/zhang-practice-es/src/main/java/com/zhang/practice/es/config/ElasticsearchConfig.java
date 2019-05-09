package com.zhang.practice.es.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.license.StartBasicRequest;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @ClassName ElasticsearchConfig
 * @Description:
 * @Author: zhangzh
 * @Date 2019/5/5 11:23
 */
@Slf4j
@Configuration
public class ElasticsearchConfig extends AbstractFactoryBean {

    /**
     * elk集群地址
     */
    @Value("${elasticsearch.ip}")
    private String hostName;

    /**
     * 端口
     */
    @Value("${elasticsearch.port}")
    private String port;

    private RestHighLevelClient restHighLevelClient;

    private static RestClientBuilder builder;

    private static RestClient restClient;

    @Override
    public void destroy() throws Exception {
        try {
            if (restHighLevelClient != null) {
                restHighLevelClient.close();
            }
        } catch (final Exception e) {
            log.error("Error closing ElasticSearch client: ", e);
        }
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public RestHighLevelClient createInstance() {
        return buildClient();
    }

    /*@Override
    public RestHighLevelClient getObject() throws Exception {
        return restHighLevelClient;
    }*/

    @Override
    public Class<?> getObjectType() {
        return restHighLevelClient.getClass();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        restHighLevelClient = buildClient();
    }

    /*private RestHighLevelClient buildClient() {
        try {
            restHighLevelClient = new RestHighLevelClient(RestClient.builder(new HttpHost(hostName, Integer.parseInt(port), "https")));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return restHighLevelClient;
    }*/

    private RestHighLevelClient buildClient() {
        try {
            builder = RestClient.builder(new HttpHost(hostName, Integer.parseInt(port), "https"));
            if(true){
                setConnectTimeOutConfig();
            }
//            if(true){
//                setMutiConnectConfig();
//            }
            restClient = builder.build();
            restHighLevelClient = new RestHighLevelClient(builder);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return restHighLevelClient;
    }


    public void setConnectTimeOutConfig(){
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,new UsernamePasswordCredentials("elastic", "5y8fzjhd78tqff37"));

        builder.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
            @Override
            public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder builder) {
                builder.setConnectTimeout(1000);
                builder.setSocketTimeout(30000);
                builder.setConnectionRequestTimeout(500);
                builder.setAuthenticationEnabled(true);
                return builder;
            }
        });
        /*
        builder.setRequestConfigCallback(requestConfigBuilder -> {requestConfigBuilder.setConnectTimeout(CONNECT_TIME_OUT);
            requestConfigBuilder.setSocketTimeout(SOCKET_TIME_OUT);
            requestConfigBuilder.setConnectionRequestTimeout(CONNECTION_REQUEST_TIME_OUT);
            return requestConfigBuilder;
        });
         */
        builder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {

            @Override
            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                SSLContext sslContext;
                try {
                    sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                        // 信任所有证书
                        @Override
                        public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                            return true;
                        }
                    }).build();
                    httpClientBuilder.setSSLContext(sslContext);
                } catch (KeyManagementException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (KeyStoreException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            }

        });
    }


    /**
     *    主要关于异步 httpclient 的连接数配置
     */
    public static void setMutiConnectConfig(){
        builder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
            @Override
            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
                httpAsyncClientBuilder.setMaxConnTotal(100);
                httpAsyncClientBuilder.setMaxConnPerRoute(100);
                return httpAsyncClientBuilder;
            }
        });
        /*
        builder.setHttpClientConfigCallback(httpClientBuilder -> {httpClientBuilder.setMaxConnTotal(MAX_CONNECT_NUM);
            httpClientBuilder.setMaxConnPerRoute(MAX_CONNECT_PER_ROUTE);
            return httpClientBuilder;
        });
        */
    }

    public static RestClient getClient(){
        return restClient;
    }
}

