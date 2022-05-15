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

import java.util.List;

import com.alibaba.p3c.pmd.lang.java.rule.AbstractAliRule;

import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceDeclaration;
import net.sourceforge.pmd.lang.java.ast.ASTForStatement;
import net.sourceforge.pmd.lang.java.ast.ASTName;
import org.jaxen.JaxenException;

/**
 * [Mandatory] Do not remove or add elements to a collection in a foreach loop. Please use Iterator to remove an item.
 * Iterator object should be synchronized when executing concurrent operations.
 *
 * @author shengfang.gsf
 * @date 2016/12/13
 */
public class DontUpdateViewInForeachCircleRule extends AbstractAliRule {

    private final static String UPDATE_VIEW = ".updateView";
    private final static String XPATH = "//ForStatement/Expression/PrimaryExpression/PrimaryPrefix/Name";
    private final static String CHILD_XPATH
        = "Statement/Block/BlockStatement/Statement/StatementExpression/PrimaryExpression/PrimaryPrefix/Name";

    public static void main(String[] args) {
        System.out.println("java.lang.Throwable: rule set java/ali-pmd not found for\n" +
            "\tat com.intellij.openapi.diagnostic.Logger.error(Logger.java:134)\n" +
            "\tat com.alibaba.p3c.idea.inspection.AliLocalInspectionToolProvider$Companion.processForRuleSet(AliLocalInspectionToolProvider.kt:203)\n" +
            "\tat com.alibaba.p3c.idea.inspection.AliLocalInspectionToolProvider$Companion.newRuleInfos(AliLocalInspectionToolProvider.kt:167)\n" +
            "\tat com.alibaba.p3c.idea.inspection.AliLocalInspectionToolProvider$Companion.initPmdInspection(AliLocalInspectionToolProvider.kt:140)\n" +
            "\tat com.alibaba.p3c.idea.inspection.AliLocalInspectionToolProvider$Companion.access$initPmdInspection(AliLocalInspectionToolProvider.kt:69)\n" +
            "\tat com.alibaba.p3c.idea.inspection.AliLocalInspectionToolProvider.<clinit>(AliLocalInspectionToolProvider.kt:118)\n" +
            "\tat sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)\n" +
            "\tat sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)\n" +
            "\tat sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)\n" +
            "\tat java.lang.reflect.Constructor.newInstance(Constructor.java:423)\n" +
            "\tat org.picocontainer.defaults.InstantiatingComponentAdapter.newInstance(InstantiatingComponentAdapter.java:193)\n" +
            "\tat com.intellij.util.pico.CachingConstructorInjectionComponentAdapter.doGetComponentInstance(CachingConstructorInjectionComponentAdapter.java:85)\n" +
            "\tat com.intellij.util.pico.CachingConstructorInjectionComponentAdapter.instantiateGuarded(CachingConstructorInjectionComponentAdapter.java:62)\n" +
            "\tat com.intellij.util.pico.CachingConstructorInjectionComponentAdapter.getComponentInstance(CachingConstructorInjectionComponentAdapter.java:45)\n" +
            "\tat com.intellij.openapi.extensions.impl.ExtensionComponentAdapter.getComponentInstance(ExtensionComponentAdapter.java:72)\n" +
            "\tat com.intellij.openapi.extensions.impl.ExtensionComponentAdapter.getExtension(ExtensionComponentAdapter.java:113)\n" +
            "\tat com.intellij.openapi.extensions.impl.ExtensionPointImpl.processAdapters(ExtensionPointImpl.java:252)\n" +
            "\tat com.intellij.openapi.extensions.impl.ExtensionPointImpl.getExtensionList(ExtensionPointImpl.java:179)\n" +
            "\tat com.intellij.openapi.extensions.impl.ExtensionPointImpl.getExtensions(ExtensionPointImpl.java:197)\n" +
            "\tat com.intellij.openapi.extensions.ExtensionPointName.getExtensions(ExtensionPointName.java:27)\n" +
            "\tat com.intellij.codeInspection.ex.InspectionToolRegistrar.ensureInitialized(InspectionToolRegistrar.java:53)\n" +
            "\tat com.intellij.codeInspection.ex.InspectionToolRegistrar.createTools(InspectionToolRegistrar.java:125)\n" +
            "\tat com.intellij.codeInspection.ex.InspectionSearchableOptionContributor.processOptions(InspectionSearchableOptionContributor.java:34)\n" +
            "\tat com.intellij.ide.ui.search.SearchableOptionPreloader.preload(SearchableOptionPreloader.java:49)\n" +
            "\tat com.intellij.openapi.application.Preloader.lambda$null$0(Preloader.java:66)\n" +
            "\tat com.intellij.openapi.progress.impl.CoreProgressManager.lambda$runProcess$2(CoreProgressManager.java:164)\n" +
            "\tat com.intellij.openapi.progress.impl.CoreProgressManager.registerIndicatorAndRun(CoreProgressManager.java:582)\n" +
            "\tat com.intellij.openapi.progress.impl.CoreProgressManager.executeProcessUnderProgress(CoreProgressManager.java:532)\n" +
            "\tat com.intellij.openapi.progress.impl.ProgressManagerImpl.executeProcessUnderProgress(ProgressManagerImpl.java:87)\n" +
            "\tat com.intellij.openapi.progress.impl.CoreProgressManager.runProcess(CoreProgressManager.java:151)\n" +
            "\tat com.intellij.openapi.application.Preloader.lambda$initComponent$1(Preloader.java:63)\n" +
            "\tat com.intellij.util.concurrency.BoundedTaskExecutor.doRun(BoundedTaskExecutor.java:227)\n" +
            "\tat com.intellij.util.concurrency.BoundedTaskExecutor.access$100(BoundedTaskExecutor.java:26)\n" +
            "\tat com.intellij.util.concurrency.BoundedTaskExecutor$2$1.run(BoundedTaskExecutor.java:200)\n" +
            "\tat com.intellij.util.ConcurrencyUtil.runUnderThreadName(ConcurrencyUtil.java:229)\n" +
            "\tat com.intellij.util.concurrency.BoundedTaskExecutor$2.run(BoundedTaskExecutor.java:194)\n" +
            "\tat java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)\n" +
            "\tat java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)\n" +
            "\tat java.lang.Thread.run(Thread.java:748)");
    }
    @Override
    public Object visit(ASTClassOrInterfaceDeclaration node, Object data) {
        if (node.isInterface()) {
            return data;
        }
        try {
            List<Node> nodes = node.findChildNodesWithXPath("//ForStatement/Statement//PrimaryExpression/PrimaryPrefix/Name");
            for (Node item : nodes) {
                if (!(item instanceof ASTName)) {
                    continue;
                }
                String variableName = item.getImage();
                if (variableName == null) {
                    continue;
                }
                ASTForStatement forStatement = item.getFirstParentOfType(ASTForStatement.class);
                List<Node> blockNodes = forStatement.findChildNodesWithXPath(CHILD_XPATH);
                for (Node blockItem : blockNodes) {
                    if (!(blockItem instanceof ASTName)) {
                        continue;
                    }
                    if (judgeName(blockItem.getImage(), variableName)) {
                        addViolationWithMessage(data, blockItem,
                            "java.kd.DontUpdateViewInForeachCircleRule.violation.msg");
                    }
                }
            }
        } catch (JaxenException e) {
            e.printStackTrace();
        }
        return super.visit(node, data);
    }

    private boolean judgeName(String name, String variableName) {
        return name != null && name.endsWith(UPDATE_VIEW);
    }

}
