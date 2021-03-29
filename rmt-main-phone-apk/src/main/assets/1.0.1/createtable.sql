drop table hse_sys_login;
CREATE TABLE [hse_sys_login] (  
  hse_sys_loginid integer not null primary key autoincrement,   
   [usercode] nvarchar(50), 
  [username] nvarchar(50), 
  [createdate] nvarchar(19), 
  [sysorder] int, 
  [autologin] INT, 
  [uuid] TEXT);