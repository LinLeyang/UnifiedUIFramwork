package com.penta.library;

import java.util.Map;

/**
 * Created by linyueyang on 2018/8/22.
 */

interface UUAdapterInterface {
    
    void notifyDataSetChanged();

    void setItemViewMap(Map<String, UUItem> map);
}
