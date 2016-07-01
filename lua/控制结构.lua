
-- 控制结构 if-else

-- 单个 if 分支 型
x = 10
if x > 0 then
    print("x is a positive number")
end

-- 两个分支 if-else 型
x = 10
if x > 0 then
    print("x is a positive number")
else
    print("x is a non-positive number")
end

-- 多个分支 if-elseif-else 型
score = 90
if score == 100 then
    print("Very good!Your score is 100")
elseif score >= 60 then
    print("Congratulations, you have passed it,your score greater or equal to 60")
--此处可以添加多个elseif
else
    print("Sorry, you do not pass the exam! ")
end

-- 与 C 语言的不同之处是 else与if 是连在一起的，若将 else 与 if 写成 "else if" 则相当于在 else 里嵌套另一个if语句，如下代码：
score = 0
if score == 100 then
    print("Very good!Your score is 100")
elseif score >= 60 then
    print("Congratulations, you have passed it,your score greater or equal to 60")
else
    if score > 0 then
        print("Your score is better than 0")
    else
        print("My God, your score turned out to be 0")
    end --与上一示例代码不同的是，此处要添加一个end
end

-- while 型控制结构

x = 1
sum = 0

while x <= 5 do
    sum = sum + x
    x = x + 1
end
print(sum)  -->output 15


-- 值得一提的是，Lua 并没有像许多其他语言那样提供类似 continue 这样的控制语句用来立即进入下一个循环迭代（如果有的话）。因此，我们需要仔细地安排循环体里的分支，以避免这样的需求。
-- 没有提供 continue ，却也提供了另外一个标准控制语句 break ，可以跳出当前循环。例如我们遍历 table ，查找值为 11 的数组下标索引：

local t = {1, 3, 5, 8, 11, 18, 21}

local i
for i, v in ipairs(t) do
    if 11 == v then
        print("index[" .. i .. "] have right value[11]")
        break
    end
end

-- for 控制结构
for i = 1, 5 do
  print(i)
end

for i = 1, 10, 2 do
  print(i)
end

-- for 泛型
local a = {"a", "b", "c", "d"}
for i, v in ipairs(a) do
  print("index:", i, " value:", v)
end

-- break，return 关键字
-- break
sum = 0
i = 1
while true do
    sum = sum + i
    if sum > 100 then
        break
    end
    i = i + 1
end
print("The result is " .. i)  -->output:The result is 14

-- return
local function add(x, y)
    return x + y
    --print("add: I will return the result " .. (x + y))
    --因为前面有个return，若不注释该语句，则会报错
end

local function is_positive(x)
    if x > 0 then
        return x .. " is positive"
    else
        return x .. " is non-positive"
    end

    --由于return只出现在前面显式的语句块，所以此语句不注释也不会报错
    --，但是不会被执行，此处不会产生输出
    print("function end!")
end

sum = add(10, 20)
print("The sum is " .. sum)  -->output:The sum is 30
answer = is_positive(-10)
print(answer)                -->output:-10 is non-positive

