package kr.co.forearlybird.dao;

import java.util.List;
import java.util.Map;

public interface BoardAdminDAO {

	List<String> getAdminID(int brd_id);

	@SuppressWarnings("rawtypes")
	int checkAdminId(Map map);

	@SuppressWarnings("rawtypes")
	int makeAdmin(Map map);

}
