1.概念
    QPS（Queries Per Second，每秒查询数）  TPS（Transactions Per Second，每秒处理事务数）
    通过show status命令查看数据库运行状态:
    Uptime：服务器已经运行的时间，单位秒
    Questions：已经发送给数据库查询数
    Com_select：查询次数，实际操作数据库的
    Com_insert：插入次数
    Com_delete：删除次数
    Com_update：更新次数
    Com_commit：事务次数
    Com_rollback：回滚次数
2.QPS和TPS计算方法：
    1、基于Questions计算出QPS
        QPS = Questions / Uptime
        mysql> show global status like 'Questions';
        mysql> show global status like 'Uptime';
    2、基于Com_commit和Com_rollback计算出TPS：
        TPS = (Com_commit + Com_rollback) / Uptime
        mysql> show global status like 'Com_commit';
        mysql> show global status like 'Com_rollback';
        mysql> show global status like 'Uptime';

