package kr.co.forearlybird.dao;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
public interface BoardAdminDAO {

	List<String> getAdminID(int brd_id);

	int checkAdminId(Map map);

	int makeAdmin(Map map);

	void deleteAdmin(Map map);

}
