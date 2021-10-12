---
layout: post
author: LIU,HONGYANG
tags: [VScode]
---



### Vscode配置原理



首先，vscode可以根据用户自定义配置，分为用户配置和工作区配置。一般情况，个人配置用户区配置即可。

其次vscode提供了setting.json文件，用于帮助用户参考自定义或者参考他人配置项，

这里提供一个[VisualStudioCode_settings.json](https://github.com/RamiroGO/VisualStudioCode_settings.json)开源的配置项，也是目前我正在使用的。



```json
{
    "workbench.editorAssociations": {
        "*.html": "default"
    },
    "editor.codeActionsOnSave": {
        "source.fixAll": true
    },
    "liveServer.settings.donotShowInfoMsg": true,
    "git.enableSmartCommit": true,
    "editor.minimap.enabled": false,
    "breadcrumbs.enabled": false,
    "editor.renderWhitespace": "none",
    "editor.renderControlCharacters": true,
    "tabnine.experimentalAutoImports": true,
    "workbench.preferredLightColorTheme": "Default Dark+",
    "workbench.colorCustomizations": {
        "tab.activeBackground": "#404040", // color de la pestaña activa.
        "tab.activeBorder": "#ff0000", // color de borde inferior en la pestaña activa.
        "tab.hoverBackground": "#00776b" // color de la pestaña activa al pasar el mouse encima.
        // "tab.inactiveBackground": "#ff0000"
    },
    "workbench.editor.decorations.colors": true,
    "workbench.editor.closeOnFileDelete": false,
    "workbench.editor.enablePreview": true,
    "workbench.iconTheme": "material-icon-theme",
    "window.autoDetectHighContrast": false,
    "window.closeWhenEmpty": true,
    "window.openWithoutArgumentsInNewWindow": "on",
    "editor.linkedEditing": true,
    // Modificar el texto y la sintaxis del editor:
    // 1. Hacer Ctrl+Shift+P
    // 2. Developer: Inspect Editor Tokens and Scopes
    // 3. Llevar el cursor hasta el tipo de texto que se pretende identificar para cambiar su color 
    // 4. Seleccionar el "textmate scopes" en el menú que se despliega, correspondiente al tipo de código que se pretende modificar su color.
    // 5. Copiar y pegar el valor de "textmate scopes" en el scope del siguiente código.
    // 	variable.other.object.js
    "editor.tokenColorCustomizations": {
        "textMateRules": [
            {
                "scope": [
                    "string.quoted.single.js"
                ],
                "settings": {
                    "foreground": "#FF0000"
                }
            },
            {
                "scope": [
                    "string.quoted.double.js"
                ],
                "settings": {
                    "foreground": "#C00000"
                }
            },
            {
                "scope": [
                    "meta.object-literal.key.js",
                    "variable.other.property.js"
                ],
                "settings": {
                    "foreground": "#569CD6"
                }
            },
            {
                "scope": [
                    "support.function.js",
                    "entity.name.function.js"
                ],
                "settings": {
                    "foreground": "#0F0"
                }
            },
            {
                "scope": [
                    "variable.other.readwrite.js"
                ],
                "settings": {
                    "foreground": "#00DDff",
                    "fontStyle": "bold"
                }
            },
            {
                "scope": [
                    "variable.other.object.js",
                    "variable.other"
                ],
                "settings": {
                    "foreground": "#56D6D6"
                }
            },
            {
                "scope": [
                    "keyword.other.sql",
                    "keyword.control.conditional.js",
                    "keyword.control.flow.js",
                    "storage.type.function.arrow.js",
                    "storage.type.js"
                ],
                "settings": {
                    "foreground": "#00F",
                    "fontStyle": "bold"
                }
            },
            {
                "scope": [
                    "punctuation.definition.heading.markdown"
                ],
                "settings": {
                    "foreground": "#A00"
                }
            },
            {
                "scope": [
                    "markup.heading.markdown"
                ],
                "settings": {
                    "foreground": "#FF0"
                }
            },
            {
                "scope": [
                    "heading.4.markdown",
                    "string.quoted.double.sql"
                ],
                "settings": {
                    "foreground": "#D0D00D",
                    "fontStyle": ""
                }
            },
            {
                "scope": [
                    "heading.5.markdown"
                ],
                "settings": {
                    "foreground": "#569CD6"
                }
            },
            {
                "scope": [
                    "comment.line.double-dash.sql"
                ],
                "settings": {
                    "foreground": "#D0D00D",
                    "fontStyle": "bold underline"
                }
            }
        ]
    },
    "editor.cursorStyle": "line",
    "editor.dragAndDrop": true,
    "editor.find.cursorMoveOnType": true,
    "editor.formatOnType": true,
    "editor.autoClosingQuotes": "always",
    "editor.autoClosingDelete": "always",
    "editor.autoSurround": "languageDefined",
    "editor.autoClosingOvertype": "always",
    "editor.autoClosingBrackets": "always",
    "editor.trimAutoWhitespace": true,
    "editor.find.autoFindInSelection": "multiline",
    "editorconfig.generateAuto": true,
    "editor.acceptSuggestionOnCommitCharacter": true,
    "editor.acceptSuggestionOnEnter": "on",
    "editor.peekWidgetDefaultFocus": "editor",
    "workbench.editor.tabCloseButton": "left",
    "editor.multiCursorPaste": "full",
    "editor.suggestSelection": "recentlyUsedByPrefix",
    "editor.guides.bracketPairs": true,
    "workbench.editor.showTabs": true,
    "workbench.editor.highlightModifiedTabs": true,
    "editor.selectionHighlight": true,
    "editor.colorDecorators": true,
    "editor.smartSelect.selectLeadingAndTrailingWhitespace": true,
    "editor.autoIndent": "advanced",
    "editor.find.seedSearchStringFromSelection": "selection",
    // "editor.columnSelection": true,
    "editor.tabCompletion": "on",
    "editor.emptySelectionClipboard": true,
    "editor.wordBasedSuggestionsMode": "allDocuments",
    "editor.wordBasedSuggestions": true,
    "vsintellicode.modify.editor.suggestSelection": "automaticallyOverrodeDefaultValue",
    "editor.bracketPairColorization.enabled": true,
    "[css]": {
        "editor.defaultFormatter": "MikeBovenlander.formate"
    },
    "[sql]": {
        "editor.defaultFormatter": "mtxr.sqltools"
    },
    "[jsonc]": {
        "editor.defaultFormatter": "vscode.json-language-features"
    },
    "editor.insertSpaces": false,
    "window.zoomLevel": -1,
    "workbench.colorTheme": "Dobri Next -C04- Material"
}
```



# vscode操作技巧



##### 如何更改提示错误：

https://stackoverflow.com/questions/43454967/disable-wavy-underline-in-vs-code

```go
 "workbench.colorCustomizations": {
    "editor.selectionBackground": "#000000",
    "editor.selectionHighlightBackground": "#16b151",
    "editorError.foreground": "#f30808",
    "editorWarning.foreground": "#af261d",
    "editorInfo.foreground": "#2a9d8f"
  }
```
##### 放大缩小：

https://blog.csdn.net/weixin_39475476/article/details/105551466#:~:text=%E5%A6%82%E4%BD%95%E6%94%B9%E5%8F%98VS%20code%E7%9A%84,%E5%B7%A6%E8%BE%B9%E7%A9%BA%E7%99%BD%E5%A4%84%E5%8D%B3%E5%8F%AF

##### vscode连接远程服务器：

https://zhuanlan.zhihu.com/p/68577071

##### vscode快捷键：

https://docs.microsoft.com/en-us/visualstudio/ide/default-keyboard-shortcuts-in-visual-studio?view=vs-2019

##### 批量修改变量：

https://blog.csdn.net/wwws1994/article/details/97014444



##### Vscode 代码显示配置:

##### [取消platue的代码提示](https://stackoverflow.com/questions/60020523/vs-code-asks-for-password-on-ech-css-save)



##### 插件使用

参考视频：https://www.bilibili.com/video/BV1ME411Y71o?p=387

