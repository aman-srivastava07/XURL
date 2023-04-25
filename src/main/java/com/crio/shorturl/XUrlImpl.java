package com.crio.shorturl;

import java.util.HashMap;
import java.util.Map;



public class XUrlImpl implements XUrl
{
    private Map<String,String> hmap = new HashMap<String,String>();
    private Map<String,Integer> hitCount = new HashMap<String,Integer>();
    //String shortUrl = "http://short.url/";  This will not work as expected, we have to declare it inside function. 
    
    @Override
    public String registerNewUrl(String longUrl)
    {
        incrementCount(longUrl);
        String shortUrl = "http://short.url/";
        if(hmap.containsKey(longUrl))
        {
            return hmap.get(longUrl);
        }
        else
        {
            String str = getAlphaNumericStringWithNoSpecialChar(9);
            shortUrl = shortUrl+str;
            hmap.put(longUrl, shortUrl);
            return hmap.get(longUrl);
        } 
    }

    @Override
    public String registerNewUrl(String longUrl, String shortUrl) 
    {
        for(Map.Entry<String,String> i: hmap.entrySet())
        {
            if(i.getValue() == shortUrl)
                return null;
        }
        hmap.put(longUrl, shortUrl);
        return hmap.get(longUrl);
    }

    @Override
    public String getUrl(String shortUrl) 
    {
        //incrementCount(shortUrl);   This is not required.
        for(Map.Entry<String,String> i: hmap.entrySet())
        {
            if(i.getValue().equals(shortUrl))
            {
                return i.getKey();
            }
        }
        return null;
    }

    @Override
    public Integer getHitCount(String longUrl) 
    {
        if(hmap.containsKey(longUrl))
        {
            return hitCount.get(longUrl);
        }
        return 0;
    }

    @Override
    public String delete(String longUrl) 
    {
       hmap.remove(longUrl);
       return hmap.get(longUrl);
    }

    // My Methods
    private static String getAlphaNumericStringWithNoSpecialChar(int n)
    {
        String alpha_numeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"+"abcdefghijklmnopqrstuvwxyz"+"0123456789";
        StringBuilder sb = new StringBuilder(n);
        for(int i=0;i<n;i++)
        {
            int index = (int) (alpha_numeric.length() * Math.random());
            sb.append(alpha_numeric.charAt(index));
        }
        return sb.toString();
    }

    private void incrementCount(String longUrl)
    {
        if(hitCount.containsKey(longUrl))
        {
            hitCount.put(longUrl, hitCount.get(longUrl)+1);
        }
        else
        {
            hitCount.put(longUrl, 1);
        }
    }
}