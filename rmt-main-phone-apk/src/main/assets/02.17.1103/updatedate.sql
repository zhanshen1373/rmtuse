update hse_sys_appmodule set layoutorder = layoutorder+1 where type='ONLINE' and layoutorder>1;
update hse_sys_appmodule set layoutorder = 2 where type='ONLINE' and code='rmt-assignstaff-phone-app';
