package com.tyj.craftshow.model;

import android.support.annotation.IntDef;

import static com.tyj.craftshow.model.NodeType.TYPE_GROUP;
import static com.tyj.craftshow.model.NodeType.TYPE_MEMBER;

/**
 * @author create by kyle_2019 on 2019/5/20 10:15
 * @package com.tyj.craftshow.model
 * @fileName NodeType
 */
@IntDef({TYPE_GROUP, TYPE_MEMBER})
public @interface NodeType {
    int TYPE_GROUP = 1;
    int TYPE_MEMBER = 2;
}
