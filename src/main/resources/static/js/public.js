//引入js，css文件操作
function  Import() {
    var importCssJs = {
        css: function(path) {
            if(!path || path.length === 0) {
                throw new Error('参数"path"错误');
            }
            var head = document.getElementsByTagName('head')[0];
            var links = document.createElement("link");
            links.href = path;
            links.rel = "stylesheet";
            links.type = "text/css";
            head.appendChild(links);
        },
        js: function(path) {
            if(!path || path.length === 0) {
                throw new Error('参数"path"错误');
            }
            var head = document.getElementsByTagName("head")[0];
            var scripts = document.createElement("script");
            scripts.src = path;
            scripts.type = "text/javascript";
            head.appendChild(scripts);
        }
    }

    //--------引入css文件
    importCssJs.css();


    //--------引入js文件
    importCssJs.js();

}