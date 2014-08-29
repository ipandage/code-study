(function(){
    console.info('22222');
    var func = {
        test1 : function() {
            console.info('func test1');
        },
        test2 :function() {
            console.info('func test2');
        }
    }

    function testfunIn1() {
        console.info('testfunIn1');
    }

    function TestfunIn2() {
        console.info('testfunIn2');
    }

    TestfunIn2.prototype.test1 = function() {
        console.info('TestfunIn2 test1');
        this.test2();
        cache.set('a' , 1);
        cache.get('a');
    }

    TestfunIn2.prototype.test2 = function() {
        console.info('TestfunIn2 test2');
    }

    var temp = {};

    var cache = {
        set : function(a, b) {
            temp.a = b;
            console.info('cache set a = ' + a + ' b = ' + b);
        },
        get : function(a) {
            console.info('cache get a = ' + temp[a]);
        }
    };

    window.TestfunIn2 = TestfunIn2;

})()

function testfunOut1() {
    console.info('testfunOut1');
    var testfunIn2  = new TestfunIn2();
    testfunIn2.test1();
}

function TestfunOut2() {
    console.info('testfunIn2');
}
TestfunOut2.prototype.test1 = function() {
    console.info('TestfunOut2 test1');
}