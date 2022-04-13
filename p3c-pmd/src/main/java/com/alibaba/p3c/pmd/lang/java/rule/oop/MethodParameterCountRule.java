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

package com.alibaba.p3c.pmd.lang.java.rule.oop;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;
import net.sourceforge.pmd.lang.java.ast.ASTFormalParameters;
import net.sourceforge.pmd.lang.java.ast.ASTMethodDeclaration;

/**
 * 功能描述
 *
 * @author kingdee
 * @since 2022-04-13
 */
public class MethodParameterCountRule extends AbstractAliRule {

    private static final Integer PARAMETER_COUNT_LIMIT = 5;

    @Override
    public Object visit(ASTMethodDeclaration methodNode, Object data) {
        try {
            // 找到每个方法的参数列表声明
            ASTFormalParameters parameters = methodNode.getFormalParameters();
            if (parameters.getParameterCount() >= PARAMETER_COUNT_LIMIT) {
                // 违反规则提示信息，第二个参数是提示信息位置，第三个参数是提示信息key，第四个参数用来替换提示信息
                // 中的占位符，这里获取的节点image属性就是方法名称
                addViolationWithMessage(data, parameters, "java.oop.MethodParameterCountRule.violation.msg",
                        new Object[] {methodNode.getMethodName()});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.visit(methodNode, data);
    }
}
