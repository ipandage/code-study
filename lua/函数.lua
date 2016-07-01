-- 函数定义
function function_name (arc)  -- arc 表示参数列表，函数的参数列表可以为空
   -- body
end

-- 由于全局变量一般会污染全局名字空间，同时也有性能损耗（即查询全局环境表的开销），因此我们应当尽量使用“局部函数”，其记法是类似的，只是开头加上 local 修饰符：
local function function_name (arc)
  -- body
end

-- 由于函数定义本质上就是变量赋值，而变量的定义总是应放置在变量使用之前，所以函数的定义也需要放置在函数调用之前。
local function max(a, b)  --定义函数 max，用来求两个数的最大值，并返回
   local temp = nil       --使用局部变量 temp，保存最大值
   if(a > b) then
      temp = a
   else
      temp = b
   end
   return temp            --返回最大值
end

local m = max(-12, 20)    --调用函数 max，找去 -12 和 20 中的最大值
print(m)                  --> output 20

-- 如果参数列表为空，必须使用 () 表明是函数调用。
local function func()   --形参为空
    print("no parameter")
end

func()                  --函数调用，圆扩号不能省

-- 函数的参数
-- 按值传递
local function swap(a, b) --定义函数swap,函数内部进行交换两个变量的值
   local temp = a
   a = b
   b = temp
   print(a, b)
end

local x = "hello"
local y = 20
print(x, y)
swap(x, y)    --调用swap函数
print(x, y)   --调用swap函数后，x和y的值并没有交换

-- 在调用函数的时候，若形参个数和实参个数不同时，Lua 会自动调整实参个数。调整规则：若实参个数大于形参个数，从左向右，多余的实参被忽略；若实参个数小于形参个数，从左向右，没有被实参初始化的形参会被初始化为 nil
local function fun1(a, b)       --两个形参，多余的实参被忽略掉
   print(a, b)
end

local function fun2(a, b, c, d) --四个形参，没有被实参初始化的形参，用nil初始化
   print(a, b, c, d)
end

local x = 1
local y = 2
local z = 3

fun1(x, y, z)         -- z被函数fun1忽略掉了，参数变成 x, y
fun2(x, y, z)         -- 后面自动加上一个nil，参数变成 x, y, z, nil

-- 变长参数
-- LuaJIT 2 尚不能 JIT 编译这种变长参数的用法，只能解释执行。所以对性能敏感的代码，应当避免使用此种形式
local function func( ... )                -- 形参为 ... ,表示函数采用变长参数

   local temp = {...}                     -- 访问的时候也要使用 ...
   local ans = table.concat(temp, " ")    -- 使用 table.concat 库函数对数
                                          -- 组内容使用 " " 拼接成字符串。
   print(ans)
end

func(1, 2)        -- 传递了两个参数
func(1, 2, 3, 4)  -- 传递了四个参数

-- 具名参数
local function change(arg) -- change 函数，改变长方形的长和宽，使其各增长一倍
  arg.width = arg.width * 2
  arg.height = arg.height * 2
  return arg
end

local rectangle = { width = 20, height = 15 }
print("before change:", "width  =", rectangle.width,
                        "height =", rectangle.height)
rectangle = change(rectangle)
print("after  change:", "width  =", rectangle.width,
                        "height =", rectangle.height)

-- 按引用传递
-- 在常用基本类型中，除了 table 是按址传递类型外，其它的都是按值传递参数。 用全局变量来代替函数参数的不好编程习惯应该被抵制，良好的编程习惯应该是减少全局变量的使用
function change(arg) --change函数，改变长方形的长和宽，使其各增长一倍
  arg.width = arg.width * 2  --表arg不是表rectangle的拷贝，他们是同一个表
  arg.height = arg.height * 2
end                  -- 没有return语句了

local rectangle = { width = 20, height = 15 }
print("before change:", "width = ", rectangle.width,
                        " height = ", rectangle.height)
change(rectangle)
print("after change:", "width = ", rectangle.width,
                       " height =", rectangle.height)

-- 函数返回值
-- Lua 具有一项与众不同的特性，允许函数返回多个值。Lua 的库函数中，有一些就是返回多个值。
local s, e = string.find("hello world", "llo")
print(s, e)  -->output 3  5

-- 返回多个值时，值之间用 “,” 隔开。
local function swap(a, b)   -- 定义函数 swap，实现两个变量交换值
   return b, a              -- 按相反顺序返回变量的值
end

local x = 1
local y = 20
x, y = swap(x, y)           -- 调用 swap 函数
print(x, y)                 --> output   20     1

-- 当函数返回值的个数和接收返回值的变量的个数不一致时，Lua 也会自动调整参数个数。
function init()             --init 函数 返回两个值 1 和 "lua"
  return 1, "lua"
end

x = init()
print(x)

x, y, z = init()
print(x, y, z)

-- 全动态函数调用
