<!--自定义轮播间隔时间-->
// $(document).ready(function(){
//     $('#demo').carousel({interval:1000});//每隔1秒自动轮播
// });
$('.carousel').carousel({
    interval: 1000
})
// var proposals = ['App', 'A', 'Appl'];
// $('#search_kw').autocomplete({
//     hints: proposals,
//     width: 300,
//     height: 30,
//     onSubmit: function(text){
//         $('#message').html('Selected: <b>' + text + '</b>');
//     }
// });
// $(document).ready(function () {
//     (function () {
//         var insertOptions = function (data, id) {
//             var result = new Array();
//             console.log(data);
//             $.each(data, function (i, item) {
//                 result.push(item.short_name);
//             });
//             //alert(result.toString());
//             console.log(result.toString());
//             $('#search_kw').autocomplete({
//                 source: result
//             });
//         }
//
//         $('#search_kw').keyup(function () {
//             var right_id = "search_kw";
//             //var url = "product/new.do?pageSize=12&pageNo=0";
//             var skeyword = $("#search_kw").val();
//
//             $.ajax({
//                 type:"get",
//                 url: "new.do?pageSize=12&pageNo=0",
//                 jsonp: "callback",
//                 data: {"skeyword": skeyword},
//                 success: function (data) {
//                     insertOptions(data, right_id);
//                 }
//             });
//         });
//
//     })();
// });

$(document).ready(function () {
    var sub = $('#sub');
    var activeRow;
    var activeMenu;
    var timer;
    var mouseInSub = false;
    sub.on('mouseenter', function (e) {
        mouseInSub = true
    }).on('mouseleave', function (e) {
        mouseInSub = false
    });
    var mouseTrack = [];
    var moveHandler = function (e) {
        mouseTrack.push({
            x: e.pageX,
            y: e.pageY
        });
        if (mouseTrack.length > 3) {
            mouseTrack.shift()
        }
    };
    $('#test')
        .on('mouseenter', function (e) {
            sub.removeClass('none');

            $(document).bind('mousemove', moveHandler)
        })
        .on('mouseleave', function (e) {
            sub.addClass('none');
            if (activeRow) {
                activeRow.removeClass('active');
                activeRow = null;
            }
            if (activeMenu) {
                activeMenu.addClass('none');
                activeMenu = null;
            }
            //解绑
            $(document).unbind('mousemove', moveHandler)
        })
        .on('mouseenter', 'li', function (e) {
            if (!activeRow) {
                activeRow = $(e.target).addClass('acrive');
                activeMenu = $('#' + activeRow.data('id'));
                activeMenu.removeClass('none');
                return
            }
            //清除
            if (timer) {
                clearTimeout(timer)
            }
            //鼠标当前坐标
            var currMousePos = mouseTrack[mouseTrack.length - 1];
            //上次的坐标
            var leftCorner = mouseTrack[mouseTrack.length - 2];
            var delay = needDelay(sub, leftCorner, currMousePos);
            if (delay) {
                // 时间触发，设置一个缓冲期
                timer = setTimeout(function () {
                    //判断
                    if (mouseInSub) {
                        return
                    }
                    activeRow.removeClass('active');
                    activeMenu.addClass('none');
                    activeRow = $(e.target);
                    activeRow.addClass('active');
                    activeMenu = $('#' + activeRow.data('id'));
                    activeMenu.removeClass('none');
                    timer = null
                }, 300)
            } else {
                var prevActiveRow = activeRow;
                var prevActiveMenu = activeMenu;
                activeRow = $(e.target);
                activeMenu = $('#' + activeRow.data('id'));
                prevActiveRow.removeClass('active');
                prevActiveMenu.addClass('none');
                activeRow.addClass('active');
                activeMenu.removeClass('none');
            }
        })
});

//<!--解决延迟引入的新问题-->
function sameSign(a, b) {
    return (a ^ b) >= 0
}

function vector(a, b) {
    return {
        x: b.x - a.x,
        y: b.y - a.y
    }
}

function vectorProduct(v1, v2) {
    return v1.x * v2.y - v2.x * v1.y
}

function isPointInTrangle(p, a, b, c) {
    var pa = vector(p, a);
    var pb = vector(p, b);
    var pc = vector(p, c);

    var t1 = vectorProduct(pa, pb);
    var t2 = vectorProduct(pb, pc);
    var t3 = vectorProduct(pc, pa);

    return sameSign(t1, t2) && sameSign(t2, t3)
}

function needDelay(elem, leftCorner, currMousePos) {
    var offset = elem.offset();

    var topLeft = {
        x: offset.left,
        y: offset.top
    };

    var bottomLeft = {
        x: offset.left,
        y: offset.top + elem.height()
    };
    return isPointInTrangle(currMousePos, leftCorner, topLeft, bottomLeft)
}

//ad关闭广告
function closed() {
    var a = document.getElementById("ad");
    a.style.display = "none";
}
function closed1() {
    var a = document.getElementById("img2");
    a.style.display = "none";
}

//渐入渐出
$(function () {
    $('#imgContainer').hover(function () {
            $('#img2').fadeIn('slow');
    }, function () {
        $('#img2').fadeOut('slow');
        });
});

window.onload=function () {
    var otxt=document.getElementById("search_kw");
    otxt.onfocus=function() {
        if (otxt.value != "") {
            otxt.value = "";
            otxt.focus();
        }
    };
        otxt.onblur=function(){
            if(otxt.value==""){
                otxt.value="请输入搜索商品";
            }
    }
};

function textup() {
    var s = document.getElementById('textarea').value;
    if (s.length > 1000) {
        document.getElementById('textarea').value = s.substring(0, 1000);
    }
}

function textdown(e) {
    textevent = e;
    if (textevent.keyCode == 8) {
        return;
    }
    if (document.getElementById('textarea').value.length >= 1000) {
        alert("此处限字1000")
        if (!document.all) {
            textevent.preventDefault();
        } else {
            textevent.returnValue = false;
        }
    }
}

var code;
function createCode(){
    code = "";
    var codeLength = 4;//验证码的长度
    var checkCode = document.getElementById("code");
    var random = new Array(0,1,2,3,4,5,6,7,8,9,'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R',
        'S','T','U','V','W','X','Y','Z');//随机数
    for(var i = 0; i < codeLength; i++) {//循环操作
        var index = Math.floor(Math.random()*52);//取得随机数的索引（0~52）
        code += random[index];//根据索引取得随机数加到code上
    }
    checkCode.value = code;//把code值赋给验证码
}

//校验验证码
function validate(){
    var inputCode = document.getElementById("input").value.toUpperCase(); //取得输入的验证码并转化为大写
    if(inputCode.length <= 0) { //若输入的验证码长度为0
        $("#check").addClass("layui-btn-disabled");
        // document.getElementById("check").disabled=true;
        alert("请输入验证码！"); //则弹出请输入验证码
    }else if(inputCode!= code.value.toUpperCase() ) { //忽略大小写
        // document.getElementById("check").disabled=true;
        $("#check").addClass("layui-btn-disabled");
        alert("验证码输入错误!!!请重新输入"); //则弹出验证码输入错误
        createCode();//刷新验证码
        document.getElementById("input").value = "";//清空文本框
    }else { //输入正确时
        // document.getElementById("check").disabled=false;
        alert("输入正确,完成注册！");
    }
}

<!-- 路由旧 ID，解决 seajs.use('select/x.x.x/select') 的历史遗留问题 -->
(function(){

    var JQ = '/jquery/1.7.2/jquery.js';
    seajs.cache['https://a.alipayobjects.com:443/gallery' + JQ] = seajs.cache['https://a.alipayobjects.com:443/jquery' + JQ];

    var GALLERY_MODULES = [
        'async','backbone','coffee','cookie','es5-safe','handlebars','iscroll',
        'jasmine','jasmine-jquery','jquery','jquery-color','json','keymaster',
        'labjs','less','marked','moment','mustache','querystring','raphael',
        'socketio','store','swfobject','underscore','zepto','ztree'
    ];

    var ARALE_MODULES = [
        'autocomplete','base','calendar','class','cookie','dialog','easing',
        'events','iframe-uploader','iframe-shim','messenger','overlay','popup',
        'position','select','switchable','tip','validator','widget'
    ];

    var util = {};
    util.indexOf = Array.prototype.indexOf ?
        function(arr, item) {
            return arr.indexOf(item);
        } :
        function(arr, item) {
            for (var i = 0; i < arr.length; i++) {
                if (arr[i] === item) {
                    return i;
                }
            }
            return -1;
        };
    util.map = Array.prototype.map ?
        function(arr, fn) {
            return arr.map(fn);
        } :
        function(arr, fn) {
            var ret = [];
            for (var i = 0; i < arr.length; i++) {
                ret.push(fn(arr[i], i, arr));
            }
            return ret;
        };

    function contains(arr, item) {
        return util.indexOf(arr, item) > -1
    }

    function map(id) {
        id = id.replace('#', '');

        var parts = id.split('/');
        var len = parts.length;
        var root, name;

        // id = root/name/x.y.z/name
        if (len === 4) {
            root = parts[0];
            name = parts[1];

            // gallery 或 alipay 开头的没有问题
            if (root === 'alipay' || root === 'gallery') {
                return id;
            }

            // arale 开头的
            if (root === 'arale') {
                // 处理 arale/handlebars 的情况
                if (contains(GALLERY_MODULES, name)) {
                    return id.replace('arale/', 'gallery/');
                } else {
                    return id;
                }
            }
        }
        // id = name/x.y.z/name
        else if (len === 3) {
            name = parts[0]

            // 开头在 GALLERY_MODULES 或 ARALE_MODULES
            if (contains(GALLERY_MODULES, name)) {
                return 'gallery/' + id;
            } else if (contains(ARALE_MODULES, name)) {
                return 'arale/' + id;
            }
        }

        return id;
    }

    var _use = seajs.use;

    seajs.use = function(ids, callback) {
        if (typeof ids === 'string') {
            ids = [ids];
        }

        ids = util.map(ids, function(id) {
            return map(id);
        });

        return _use(ids, callback);
    }

})();

<!-- monitor 防错代码 -->

(function(win){
    if(!win.monitor){win.monitor = {};}

    var METHODS = ["lost", "log", "error", "on", "off"];
    var l=METHODS.length;
    for(var i = 0,method;i < l; i++){
        method = METHODS[i];
        if("function" !== typeof win.monitor[method]){
            win.monitor[method] = function(){};
        }
    }
})(window);

window.Tracker && Tracker.start &&  Tracker.start();

/*
   * 获取cookie
   * @param {String} ctoken
   */
function getCookie(name) {
    if (document.cookie.length > 0) {
        var begin = document.cookie.indexOf(name + '=');
        if (begin !== -1) {
            begin += name.length + 1;
            var end = document.cookie.indexOf(';', begin);
            if (end === -1) {
                end = document.cookie.length;
            }
            return unescape(document.cookie.substring(begin, end));
        }
    }
    return null;
}
window.onload = function() {
    var globalNoticeSsl = document.getElementById('J-global-notice-ssl');
    if (globalNoticeSsl) {
        var sslUpgradeTag = getCookie('ssl_upgrade');
        if (sslUpgradeTag && sslUpgradeTag === '1') {
            // 展示升级公告
            globalNoticeSsl.setAttribute('class', 'global-notice-announcement');
        } else {
            // 删除升级公告
            globalNoticeSsl.parentNode.removeChild(globalNoticeSsl);
        }
    }
}
var cimg = new Image(1,1);
cimg.onload = function() {
    cimg.onload = null;
};
cimg.src = "https://ynuf.alipay.com/service/clear.png?xt=P1b4d5a35c694c47217bd1fb3cbb76da9&xa=";

seajs.use(['$','arale/tip/1.2.2/tip'], function($,Tip) {
    $('#J_adShowTrigger').length && new Tip({
        trigger: '#J_adShowTrigger',
        content: '<div class="ad-wrap"><div class="ad-title">'+$('#J_adName').val()+'</div><div class="ad-cnt">'+$('#J_adInfo').val()+'</div></div>',    // 提示框显示的内容
        theme: 'white',
        inViewport: true,
        arrowPosition: 2
    });
});

try {
    (function () {
        var loadOnlineServer = function() {
            seajs.config({
                comboExcludes: /\/u\/(js|css|cschannel|ecmng)\//,
                alias: {
                    '$': 'jquery/jquery/1.7.2/jquery',
                    'onlineServerConfig': 'https://os.alipayobjects.com/rmsportal/iwBOQWtuJpTikoO.js',
                    'portalServerConfig': 'https://os.alipayobjects.com/rmsportal/FiPHyRpEbxSvFkDoPXIQ.js',
                    'merchantServerConfig': 'https://gw.alipayobjects.com/os/cschannel/acMWoiSyXYEmIyNRMFNg.js',
                    'customerServerConfig': 'https://gw.alipayobjects.com/os/cschannel/eKIrsHTTgHXrEJIaDKxq.js',
                    'koubeiServerConfig': 'https://gw.alipayobjects.com/os/cschannel/pQmbmblGTxzzURaFbUca.js',
                    'defaultDataConfig': 'https://a.alipayobjects.com/u/js/201311/1acIoVU1Xx.js',
                    'onlineServerJS': 'https://gw.alipayobjects.com/os/rmsportal/qCVYBmFVhtCyXvSTVdGX.js',//云客服匹配
                    'onlineServerCSS': 'https://gw.alipayobjects.com/os/rmsportal/doVVVkUglHpuXRbyNEvN.css'//云客服通用样式
                }
            });
            seajs.use(['onlineServerConfig', 'portalServerConfig','merchantServerConfig','koubeiServerConfig', 'customerServerConfig'], function(){
                jQuery(function(){
                    window.OS = OS = {},
                        OS.server = {
                            cliveServer: 'https://clive.alipay.com',
                            cschannelServer: 'https://cschannel.alipay.com',
                            initiativeServer: 'https://webpushgw.alipay.com',
                            cshallServer: 'https://cshall.alipay.com'
                        },
                        OS.params = {
                            'uid': ''
                        };
                    var tradeNos4Clive = '' || '';
                    OS.params.featureStr = "{'tradeNos':'" + tradeNos4Clive + "'}";
                    OS.config = {
                        onlineServerURL: OS.server.cliveServer + '/csrouter.htm',
                        portalServerURL: OS.server.cschannelServer + '/csrouter.htm',
                        newPortalServerURL: OS.server.cschannelServer + '/newPortal.htm',
                        webpushFlashURL: 'https://t.alipayobjects.com/tfscom/T1JsNfXoxiXXXXXXXX.swf',
                        onlineServerIconDefault: 'https://a.alipayobjects.com/u/css/201401/1v9cu1dxaf.css' //在线客服默认图片
                    };
                    seajs.use('onlineServerCSS');
                    seajs.use('onlineServerJS');
                });
            });
        }
        var bindOnlineServer = function(func){
            var w = window;
            if (w.attachEvent) {
                w.attachEvent('onload', func);
            } else {
                w.addEventListener('load', func, false);
            }
        };
        window.initOnlineServer = function() {
            var w = window, o = 'seajs', d = document;
            if(w[o]) { return loadOnlineServer() }
            var s = d.createElement("script")
            s.id = o + "node"
            s.charset = "utf-8";
            s.type = "text/javascript";
            s.src = "https://a.alipayobjects.com/??seajs/seajs/2.1.1/sea.js,jquery/jquery/1.7.2/jquery.js";
            var head = d.head || d.getElementsByTagName( "head" )[0] || d.documentElement;
            head.appendChild(s);
            s.onload = s.onreadystatechange = function(){ if (!s.readyState || /loaded|complete/.test(s.readyState)) { loadOnlineServer() } };
        };
        if (!window.isLazyLoadOnlineService) {
            bindOnlineServer(initOnlineServer);
        };
    })();
} catch (e) {
    window.console && console.log && console.log(e);
    window.Tracker && Tracker.click('onlineServer-error-init-' + e);
}

(function(){
    var prop = {
        "WMode": 0,
        "PK": "PYV7wxnFqz1ar0evEZ+3gpPQIGav7lkZ0GprOPMSXvioo3B9gV0JH8y0fzEdabPVndB2QT1Muap5c59sZ7za9VsSamx2id4qnFIrfk+P2bxscZ38y07sI4K15KQazWCYY73YlLNJFpAbQ004dUD87yk3wtC6iXbEXIqm2OsAujBih91ybG+GIS0liobzutW4i5KS8f0XuXCd76ujMeQD+jQaden9eJxriRr9hJWTFR0ZufIHkxm3aq7pFTLd2Ic65ka6Eml4DpsksZYE1u8XPH6CQd0sXjz1pMHe2Pt9e91cvYnb96rDognVA6dR9PEjnA35ZOmgaVNDQqTSZ6zlyyg/2flVtmaVlaTGDqNiilWZjaKOTM1WJRFqX9JMPfD0DraoF43SHO6ZcdmqBIqSBMI6uEUrqTnD2fc2AEqUTpJdMWTPZ/+eW0F37whEI7Men09JnJe6cgkB5HpvNNJP/rYfB5wMwP3lw7+o02EmOLLAC46IWQkRAMOwxq6+t7tTMQxqOfwTrMWouC+Lr2AiokG0tlK51Ipd+CMyGuvApyPzzvbDYdPf5Sn804KnbJFHWDJ7WwBWTBx2V8iP25T0CcbPanG5bIMSt9D9GN/66RBMILkJfWsBnbQel99BlLT+2J6SLHOay+Cyavah7Q7QfcjTFOcIh7UpocfzDUUPF34=",
        "TS": "Mjg5MjYxODA1MTk4",
        "BMode": {
            "DMode": true,
            "ReadOnly": false,
            "MaxLength": 20
        }
    };
    var renderArr = 'R',
        sensorArr = '',
        tolerate = true,
        options = {
            upgrade: '',
            id: 'payPasswd',
            prodType: '',
            sid: 'web|excashier_payment_pwd_control|e60ff177-31b5-4615-ab79-6be49211dc96RZ25'
        },
        renderOptions = {
            downloadPath: '',
            downloadServer: 'https://download.alipay.com',
            securityCenterServer: 'https://securitycenter.alipay.com',
            container: "payPasswd_container",
            R: {
                id: 'payPasswd_rsainput',
                hidnId: 'payPasswd',
                PK: "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAo0z/L+pelCPu6DwDFAY/3ITzesr8lnNmYjHht4XUJvLYYBwvDbHMc8xi9sPK9ohVHIKRVLVmmZ9SdmuWYN9HzCyyZ6kEHx+IDBPnulwjdeN/N0w25mVRhYDWxJ2/1C6cPIuNcISchOQdGKuAC0xR37i/kWH9sjBidAQjageYgQoj1HX81flZaPve75Esue85AHZ0VIurjwx7uEuxvQtvCIUvX1bbF13TIYuTbJbn/LrNHby1Kxp42ggNUjAkYUVSF7SC3UP+YGKruii7Vh1UnJ/rpVhjdt3It8le9px8H4Ltt9N3hzU17rBnFpp2ZnmiZVtlfMvsStY54Fl5cSJVxQIDAQAB",
                TS: "Mjg5MjYxODA1MTk4",
                alieditUpgradeVersions: "",
                useSilentInstallation: false,
                useKS: true,
                tabindex: "",
                container: "payPasswd_container",
                ksk: '60de924b-d54d-454f-bfef-3ab62d43a23a',             useSixDigitPassword: false           },
            C1: {
                id: "edit_payPasswd",
                name: "edit_payPasswd",
                hidnId: "payPasswd",
                width: "180",
                height: "24",
                tabindex: "",
                container: "payPasswd_container",
                passwordMode: "1",
                timestamp: "8428926180",
                pk: "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDS92pDVyWNT7dzG9zH0opH44z9FayCZTX5iqGUxUjPi667IkyaqrsmDPqKsJp47lJ29lzs+Qv8zjPPdmnxjFteMrfpc4ui24gL1iZnchwX87Ox/+Xrm8HFmKlhmUO9n/QgTT+Nz1RGMEN1+HijvsoAhS0TS8XjSfzRkrwvK2pJQIDAQAB",
                alieditUpgradeVersions: ""
            },
            C2: {
                id: "edit_payPasswd",
                name: "edit_payPasswd",
                hidnId: "payPasswd",
                width: "180",
                height: "24",
                tabindex: "",
                container: "payPasswd_container",
                passwordMode: "1",
                timestamp: "8428926180",
                pk: "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDS92pDVyWNT7dzG9zH0opH44z9FayCZTX5iqGUxUjPi667IkyaqrsmDPqKsJp47lJ29lzs+Qv8zjPPdmnxjFteMrfpc4ui24gL1iZnchwX87Ox/+Xrm8HFmKlhmUO9n/QgTT+Nz1RGMEN1+HijvsoAhS0TS8XjSfzRkrwvK2pJQIDAQAB",
                alieditUpgradeVersions: "",
                handler: "light.page",
                prop: light.escapeHTML(light.inspect(prop)),
                useKS: true,
                ksk: '60de924b-d54d-454f-bfef-3ab62d43a23a'           }
        },
        sensorOptions = {
            websocketPorts: '27382,45242',
            controlCheckTimeout: '3000'
        };

    var passwordProduct = new alipay.security.Password(options, renderArr, sensorArr, tolerate, renderOptions, sensorOptions);

    passwordProduct.onReady(function () {
        light.node(this.renderable ? '#J_edit_prompt_default' : '#J_edit_prompt_noEdit').removeClass('fn-hide');
    });
    passwordProduct.onReady(function(){
        alipay.security.snowden.report();
    });
    if (light.page.scProducts) {
        light.page.scProducts.push(passwordProduct);
    }
    if (light.page.products) {
        light.page.products['payPasswd'] = passwordProduct;
    }
    alipay.security.useMultiplePolicy = true;
})()

window.Smartracker && Smartracker.sow && Smartracker.sow();

$('#myTab home').click(function (e) {
    e.preventDefault()
    $(this).tab('show')
})

//搜索实现
function key()
{
    localStorage.value=$("#search_kw").val();
}

function search(){
    var flag=0;
    key();
    var keyword = localStorage["value"];
    $.get("product/new.do?pageSize=12&pageNo=0" , function (data) {
        $(data.data).each(function (index, item) {
            if ((item.title.toUpperCase()).indexOf(keyword.toUpperCase())!=-1) {
                flag=1;
                window.location.href = '/mall/search.html';
            }
        });
        if(!flag)
        {
            alert("无此商品，请选择其他商品");
            window.location.href = '/mall/search.html';
        }
    });
}

function entersearch(event) {
    var e = event;
    if (e.keyCode == 13) { // enter 键
        search();
    }
}

function appendToPage1(div, data) {
    var keyword1=localStorage["value"];
    $(data).each(function (index, item) {
        if((item.title.toUpperCase()).indexOf(keyword1.toUpperCase())!=-1) {
            var productDiv = $("<div class='layui-col-md3 layui-col-xs6'></div>");
            var conetDiv = $("<div class='card layui-anim layui-anim-scale' style='height:300px'></div>");
            productDiv.append(conetDiv);
            var imgdiv = $("<div class='product_image'><img src=" + item.image + "></div>");
            conetDiv.append(imgdiv);
            var priceDiv = $("<div style='text-align: left;'>商城价：<span class='price1'>" + item.shopPrice +"￥"+ "</span></div>");
            conetDiv.append(priceDiv);
            conetDiv.append($("<div class='product_title' style='height:50px'><a href='/mall/product/get.html?id=" + item.id + "'>" + item.title + "</a></div>"));
            div.append(productDiv);
        }
    });
}


$(".a-upload").on("change","input[type='file']",function(){
    var filePath=$(this).val();
    if(filePath.indexOf("jpg")!=-1 || filePath.indexOf("png")!=-1){
        $(".fileerrorTip").html("").hide();
        var arr=filePath.split('\\');
        var fileName=arr[arr.length-1];
        $(".showFileName").html(fileName);
    }else{
        $(".showFileName").html("");
        $(".fileerrorTip").html("您未上传文件，或者您上传文件类型有误！").show();
        return false
    }
})