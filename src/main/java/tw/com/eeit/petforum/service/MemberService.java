package tw.com.eeit.petforum.service;

import java.sql.Connection;
import java.util.List;

import tw.com.eeit.petforum.model.bean.Likes;
import tw.com.eeit.petforum.model.bean.Member;
import tw.com.eeit.petforum.model.bean.Pet;
import tw.com.eeit.petforum.model.dao.LikesDAO;
import tw.com.eeit.petforum.model.dao.MemberDAO;
import tw.com.eeit.petforum.model.dao.PetDAO;
import tw.com.eeit.petforum.util.ConnectionFactory;

public class MemberService {

	
	public Member login(String email, String password) {
		try (Connection conn = ConnectionFactory.getConnection()) {

			MemberDAO memberDAO = new MemberDAO(conn);
			Member m = memberDAO.findMemberByEmailAndPassword(email,password);
			return m;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public List<Pet> getAllPet(){
		try (Connection conn = ConnectionFactory.getConnection()) {

			PetDAO petDAO = new PetDAO(conn);
			List<Pet> petList = petDAO.findAllPetWithMember();
			return petList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 呼叫此方法並傳入Likes物件(須包含日期、會員ID、寵物ID)以切換「按讚」狀態。<br>
	 * 1.若資料庫有按讚記錄則移除<br>
	 * 2.若資料庫無按讚記錄則增加<br>
	 * 
	 * @param likes 單筆按讚記錄，須包含日期、會員ID、寵物ID
	 * @return "like!" : 若是新增按讚記錄到資料庫 ； "unlike!" : 若是從資料庫刪除按讚記錄 ； "error!" : 未預期的錯誤
	 */
 	public String switchLikeStatus(Likes likes) {
		try (Connection conn = ConnectionFactory.getConnection()) {

			LikesDAO lDAO = new LikesDAO(conn);
			Likes likesFromDB = lDAO.findLikeByMemberIDAndPetID(likes);

			if (likesFromDB == null) {
				lDAO.addLike(likes);
				return "like!";
			}

			if (likesFromDB != null) {
				lDAO.removeLike(likes);
				return "unlike!";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error!";
	}
}
