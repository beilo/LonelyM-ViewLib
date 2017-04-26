# LonelyM-ViewLib



###### gradle
``` gradle
compile 'com.lonely-mushroom:viewlib:0.0.1'
```

##### 更新js弹窗
``` java
DialogUpdateWidget.getInstance().createDialog(context, true).show();
```

##### Toast
``` java
ToastWidget.getInstance().createToast(context, "给我提示", ToastWidget.STATUS_SUCCESS).show();
```

##### 等候框
``` java
DialogWidget.getInstance().createLoadingDialog(context, true).show();
```

##### 底部选择器
``` java
SheetBean bean = new SheetBean();
bean.setTitle("测试");
List<BensEntity> list = new ArrayList<>();
list.add(new BensEntity("1"));
list.add(new BensEntity("2"));
list.add(new BensEntity("3"));
bean.setBtns(list);
SheetViewWidget.getInstance().createFragment(MainActivity.this, bean, new SheetViewWidget.SheetViewListener() {
	@Override
	public void listener(String title) {
	    ToastWidget.getInstance().createToast(context, title, ToastWidget.STATUS_SUCCESS).show();
	}
});
```

##### 选择器
``` java
AlertBean alertBean = new AlertBean();
alertBean.setTitle("测试");
alertBean.setMessage("message");
alertBean.setIsJudgment(true);
AlertWidget.getInstance().createFragment(context, alertBean, new AlertWidget.AlertManagerListener() {
	@Override
	public void listener(boolean flag) {
		ToastWidget.getInstance().createToast(context, flag + "", ToastWidget.STATUS_SUCCESS).show();
    }
});
```

