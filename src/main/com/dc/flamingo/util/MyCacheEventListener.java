package com.dc.flamingo.util;

import com.opensymphony.oscache.base.events.CacheEntryEvent;
import com.opensymphony.oscache.base.events.CacheEntryEventListener;
import com.opensymphony.oscache.base.events.CacheGroupEvent;
import com.opensymphony.oscache.base.events.CachePatternEvent;
import com.opensymphony.oscache.base.events.CachewideEvent;

/**
 * 
* @ClassName: MyCacheEventListener
* @Description: 缓存操作事件，需要时可以增加功能
* @author lizz
* @date Mar 6, 2012 4:54:41 PM
*
 */
public class MyCacheEventListener  implements CacheEntryEventListener{
	 /**
     * Event fired when an entry is added to the cache.
     */
    public void  cacheEntryAdded(CacheEntryEvent event){
    	
    }

    /**
     * Event fired when an entry is flushed from the cache.
     */
    public void cacheEntryFlushed(CacheEntryEvent event){
    	
    }

    /**
     * Event fired when an entry is removed from the cache.
     */
    public void cacheEntryRemoved(CacheEntryEvent event){
    	
    }

    /**
     * Event fired when an entry is updated in the cache.
     */
    public void cacheEntryUpdated(CacheEntryEvent event){
    	
    }

    /**
     * Event fired when a group is flushed from the cache.
     */
    public void cacheGroupFlushed(CacheGroupEvent event){
    	
    }

    /**
     * Event fired when a key pattern is flushed from the cache.
     * Note that this event will <em>not</em> be fired if the pattern
     * is <code>null</code> or an empty string, instead the flush
     * request will silently be ignored.
     */
    public void cachePatternFlushed(CachePatternEvent event){
    	
    }

    /**
     * An event that is fired when an entire cache gets flushed.
     */
    public void cacheFlushed(CachewideEvent event){
    	
    }

} 