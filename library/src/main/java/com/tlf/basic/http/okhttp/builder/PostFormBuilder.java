package com.tlf.basic.http.okhttp.builder;


import com.google.gson.Gson;
import com.tlf.basic.http.okhttp.request.PostFormRequest;
import com.tlf.basic.http.okhttp.request.RequestCall;
import com.tlf.basic.http.okhttp.utils.SupportLogger;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tanlifei on 15/12/14.
 */
public class PostFormBuilder extends OkHttpRequestBuilder implements HasParamsable
{
    /*
    * 请求接口参数输出过滤
    */
    public static final String OKHTTP_PARAMS = "okhttp_params";
    private List<FileInput> files = new ArrayList<>();

    @Override
    public RequestCall build()
    {

        return new PostFormRequest(url, tag, params, headers, files).build();
    }

    public PostFormBuilder addFile(String name, String filename, File file)
    {
        files.add(new FileInput(name, filename, file));
        return this;
    }

    public static class FileInput
    {
        public String key;
        public String filename;
        public File file;

        public FileInput(String name, String filename, File file)
        {
            this.key = name;
            this.filename = filename;
            this.file = file;
        }

        @Override
        public String toString()
        {
            return "FileInput{" +
                    "key='" + key + '\'' +
                    ", filename='" + filename + '\'' +
                    ", file=" + file +
                    '}';
        }
    }

    //
    @Override
    public PostFormBuilder url(String url)
    {
        this.url = url;
        return this;
    }

    @Override
    public PostFormBuilder tag(Object tag)
    {
        this.tag = tag;
        return this;
    }

    @Override
    public PostFormBuilder params(Map<String, String> params)
    {
        this.params = params;
        SupportLogger.json(OKHTTP_PARAMS,new Gson().toJson(params));
        return this;
    }

    @Override
    public PostFormBuilder paramsForJson(Map<String, Object> mapParams)
    {
        if (this.params == null)
        {
            this.params = new LinkedHashMap<>();
        }
        this.params.put("json", new Gson().toJson(mapParams));
        SupportLogger.json(OKHTTP_PARAMS,new Gson().toJson(mapParams));
        return this;
    }

    @Override
    public PostFormBuilder addParams(String key, String val)
    {
        if (this.params == null)
        {
            params = new LinkedHashMap<>();
        }
        params.put(key, val);
        SupportLogger.json(OKHTTP_PARAMS,"{'"+key+"':'"+val+"'}");
        return this;
    }



    @Override
    public PostFormBuilder addParamsForJson(String key, String val)
    {
        if (this.params == null)
        {
            params = new LinkedHashMap<>();
        }
        params.put("json", "{'"+key+"':'"+val+"'}");
        SupportLogger.json(OKHTTP_PARAMS,"{'"+key+"':'"+val+"'}");
        return this;
    }

    @Override
    public PostFormBuilder headers(Map<String, String> headers)
    {
        this.headers = headers;
        return this;
    }


    @Override
    public PostFormBuilder addHeader(String key, String val)
    {
        if (this.headers == null)
        {
            headers = new LinkedHashMap<>();
        }
        headers.put(key, val);
        return this;
    }


}
