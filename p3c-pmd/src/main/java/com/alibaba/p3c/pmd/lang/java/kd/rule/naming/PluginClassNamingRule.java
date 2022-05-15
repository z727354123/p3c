/*
 * Copyright 1999-2017 Alibaba Group.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.p3c.pmd.lang.java.kd.rule.naming;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceDeclaration;
import org.jaxen.JaxenException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * [Mandatory] Abstract class names must start with Abstract or Base.
 *
 * @author changle.lq
 * @date 2017/04/16
 */
public class PluginClassNamingRule extends AbstractAliRule {
    private static final String EXTEND_TYPE = "ExtendsList/ClassOrInterfaceType";

    private static Map<String, String> NAME_MAP = new HashMap<>();

    static {
        // key 插件父类名, val 插件后缀
        // 单据插件
        NAME_MAP.put("AbstractBillPlugIn", "EditPlugin");
        NAME_MAP.put("HRCoreBaseBillEdit", "EditPlugin");
    }

    @Override
    public Object visit(ASTClassOrInterfaceDeclaration node, Object data) {
        try {
            List<Node> ext = node.findChildNodesWithXPath(EXTEND_TYPE);
            if (ext != null && ext.size() > 0) {
                Node item = ext.get(0);
                String clsName = node.getImage();
                String fatherClsName = item.getImage();
                String endName = NAME_MAP.get(fatherClsName);
                if (endName != null && !clsName.endsWith(endName)) {
                    addViolationWithMessage(data, node, "java.kd.PluginClassNamingRule.violation.msg",
                            new Object[] {clsName, endName});
                }
            }
        } catch (JaxenException e) {
            e.printStackTrace();
        }
        return super.visit(node, data);
    }
}
