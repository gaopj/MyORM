package gpj.orm.test;

import gpj.orm.core.Query;
import gpj.orm.core.QueryFactory;

/**
 * 测试效率
 * @author gpj
 *
 */
public class Test2 {

	public static void test01(){
		Query q = QueryFactory.createQuery();
		System.out.println(q.queryValue("select count(*) from t_user where id<3", null));
	}
	public static void main(String[] args) {
		long a = System.currentTimeMillis();
		for(int i=0;i<300;i++){
			test01();
		}

		long b = System.currentTimeMillis();
		System.out.println(b-a);
	}

}
