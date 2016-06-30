
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
