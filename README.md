# QI
JAVAWeb 大作业：问卷调查系统设计与实现

## errCode值说明：

- 601 用户名已经存在

- 602 用户不存在

- 611 用户名或密码错误

- 666 参数异常

- 667 越权访问


**服务端返回的所有数据都是JSON形式的数据**

### /register接口说明

请求方式：`POST`

请求参数：
1. username String 不可为空
2. password String 不可为空

响应说明：
1. 如果操作成功，返回errCode：0
2. 如果操作失败，返回errCode：611


### /login接口说明：

请求方式：`POST`

请求参数：
1. username  String  不可为空
2. password String  不可为空

响应说明：
1. 成功登录后返回用户的相关信息
2. 登录失败返回错误码：611


### /paper接口说明：

请求方式：`GET`
1. paperId int 不可为空
2. userId int 不可为空

响应说明：
1. 操作成功，返回paperId对应的paper的相关信息
2. 操作失败，返回错误码：666、667。

请求方法：`POST`

请求参数：
1. userId int 不可为空
2. title String 不可为空
3. isPublish bool 不可为空
4. publishTime TimeStamp格式 不可为空
5. endTime TimeStamp格式 不可为空

响应说明：
1. 操作成功，返回errCode:0
2. 操作失败，返回errCode:666