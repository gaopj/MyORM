package gpj.orm.test;

import gpj.orm.core.MySqlQuery;
import gpj.orm.core.Query;
import gpj.orm.core.QueryFactory;

/**
 * 客户端调用测试类
 * @author gpj
 *
 */
public class Test {

	public static void main(String[] args) {
		Query q = QueryFactory.createQuery();
		System.out.println(q.queryValue("select count(*) from t_user where id<3", null));
	}

}
