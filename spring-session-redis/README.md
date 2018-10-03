# Spring Session

## 使用Redis保存Session


会话id存放在"spring:session:sessions:expires"开头的key中
`spring:session:sessions:expires:8e4d09da-84d0-4a4d-b189-76be30117bd6`

会话详细信息保存在spring:session:sessions:8e4d09da-84d0-4a4d-b189-76be30117bd6对应的Hash数据结构中
```
127.0.0.1:0>hgetall spring:session:sessions:8e4d09da-84d0-4a4d-b189-76be30117bd6
 1)  "lastAccessedTime"
  2)  "\xAC\xED\x00\x05sr\x00\x0Ejava.lang.Long;\x8B\xE4\x90\xCC\x8F#\xDF\x02\x00\x01J\x00\x05valuexr\x00\x10java.lang.Number\x86\xAC\x95\x1D\x0B\x94\xE0\x8B\x02\x00\x00xp\x00\x00\x01f9#\xB0v"
  3)  "maxInactiveInterval"
  4)  "\xAC\xED\x00\x05sr\x00\x11java.lang.Integer\x12\xE2\xA0\xA4\xF7\x81\x878\x02\x00\x01I\x00\x05valuexr\x00\x10java.lang.Number\x86\xAC\x95\x1D\x0B\x94\xE0\x8B\x02\x00\x00xp\x00\x00\x07\x08"
  5)  "sessionAttr:name"
  6)  "\xAC\xED\x00\x05t\x00\x05taobi"
  7)  "creationTime"
  8)  "\xAC\xED\x00\x05sr\x00\x0Ejava.lang.Long;\x8B\xE4\x90\xCC\x8F#\xDF\x02\x00\x01J\x00\x05valuexr\x00\x10java.lang.Number\x86\xAC\x95\x1D\x0B\x94\xE0\x8B\x02\x00\x00xp\x00\x00\x01f9 \xD5\xD8"
```

通过`session.setAttribute("name", "taobi");`存放的session值，保存在spring:session:sessions:8e4d09da-84d0-4a4d-b189-76be30117bd6的`sessionAttr:name`的field上。



