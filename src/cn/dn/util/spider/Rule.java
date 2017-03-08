package cn.dn.util.spider;
/**
 * 规则类
 * @author  大牛哥 
 * @E-mail:201309512@qq.com 
 * @date 创建时间：2017年3月7日 上午12:16:15
 * @version 1.0
 * @parameter
 * @since
 * @return  */

public class Rule  
{  
    /** 
     * 链接 
     */  
    private String url;  
  
    /** 
     * 参数集合 
     */  
    private String[] params;  
    /** 
     * 参数对应的值 
     */  
    private String[] values;  
  
    /** 
     * 对返回的HTML，第一次过滤所用的标签，请先设置type 
     */  
    private String resultTagName;  
  
    /** 
     * CLASS / ID / SELECTION 
     * 设置resultTagName的类型，默认为ID  
     */  
    private int type = ID ;  
      
    /** 
     *GET / POST 
     * 请求的类型，默认GET 
     */  
    private int requestMoethod = GET ;   
      
    public final static int GET = 0 ;  
    public final static int POST = 1 ;  
      
  
    public final static int CLASS = 0;  
    public final static int ID = 1;  
    public final static int SELECTION = 2;  
  
    public Rule()  
    {  
    }  
  
    /**
     * 
     * @param url
     * @param params 参数
     * @param values 对应值
     * @param resultTagName 参数类型
     * @param type 类型：Class/ID/Select
     * @param requestMoethod 请求方法
     */
    public Rule(String url, String[] params, String[] values,  
            String resultTagName, int type, int requestMoethod)  
    {  
        super();  
        this.url = url;  
        this.params = params;  
        this.values = values;  
        this.resultTagName = resultTagName;  
        this.type = type;  
        this.requestMoethod = requestMoethod;  
    }  
  
    public String getUrl()  
    {  
        return url;  
    }  
  
    public void setUrl(String url)  
    {  
        this.url = url;  
    }  
  
    public String[] getParams()  
    {  
        return params;  
    }  
  
    public void setParams(String[] params)  
    {  
        this.params = params;  
    }  
  
    public String[] getValues()  
    {  
        return values;  
    }  
  
    public void setValues(String[] values)  
    {  
        this.values = values;  
    }  
  
    public String getResultTagName()  
    {  
        return resultTagName;  
    }  
  
    public void setResultTagName(String resultTagName)  
    {  
        this.resultTagName = resultTagName;  
    }  
  
    public int getType()  
    {  
        return type;  
    }  
  
    public void setType(int type)  
    {  
        this.type = type;  
    }  
  
    public int getRequestMoethod()  
    {  
        return requestMoethod;  
    }  
  
    public void setRequestMoethod(int requestMoethod)  
    {  
        this.requestMoethod = requestMoethod;  
    }  
  
}  