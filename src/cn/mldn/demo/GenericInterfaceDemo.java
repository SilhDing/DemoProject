package cn.mldn.demo;

interface IMessage<T> {
	public void print(T t);
}

class MessageImpl<T> implements IMessage<T> {
	// still use generic class while implementing a generic interface
	public void print(T t) {
		System.out.println(t);
	}
}

class MessageImpl2 implements IMessage<String> {
	// do not use generic class anymore while implementing a generic interface
	public void print(String t) {
		System.out.println(t);
	}
	
}


public class GenericInterfaceDemo {
	public static void main(String[] args) {
		IMessage<String> msg = new MessageImpl<String>();
		msg.print("Hello");
		
		IMessage<String> msg2 = new MessageImpl2();
		msg2.print("Hello222");
	}
}
