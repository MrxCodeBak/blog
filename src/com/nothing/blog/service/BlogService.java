package com.nothing.blog.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.nothing.blog.domain.index_domain.ArticleSo;
import com.nothing.blog.domain.pt_domain.Article;
import com.nothing.blog.domain.pt_domain.Link;
import com.nothing.blog.domain.pt_domain.Menu;
import com.nothing.blog.domain.pt_domain.Remark;
import com.nothing.blog.domain.pt_domain.User;
import com.nothing.blog_tools.page.PageModel;

/**
 * 博客项目业务层
 * @author: Nothing
 * @email: 761328790@qq.com
 * @company: Sichuan Agricultural University
 * @date 2015年7月29日
 */
public interface BlogService {

	/**
	 * 异步登陆 Method
	 * @param username
	 * @param password
	 * @return
	 */
	String login(String username, String password);

	/**
	 * 跳转到后台页面时准备数据 Method
	 * @return
	 */
	Map<String, Integer> readyData();

	/**
	 * 后台主页-异步加载【首页】所需数据 Method
	 * @return
	 */
	String ajaxLoadMenu_SY();

	/**
	 * 后台主页-异步加载【用户管理】所需数据 Method
	 * @param pageModel 
	 * @return
	 */
	String ajaxLoadMenu_YH(PageModel pageModel);

	/**
	 * 后台主页-跳到修改用户页面，准备个人信息所需数据 Mthod
	 * @param userId
	 * @return
	 */
	String ajaxLoadUpdateUserInfo_YH(String userId);

	/**
	 * 后台主页-异步修改个人信息 Mthod
	 * @param user
	 * @return
	 */
	String ajaxUpdateUserInfo_YH(User user);

	/**
	 * 后台主页- 异步删除用户 Mthod
	 * @param userId
	 * @return
	 */
	String ajaxDeleteUserInfo_YH(String userId);

	/**
	 * 后台主页- 异步添加用户 Mthod
	 * @param user
	 * @return
	 */
	String ajaxAddUserInfo_YH(User user);

	/**
	 * 后台主页- 异步校验用户名是否已经存在
	 * @param username
	 * @return
	 */
	String ajaxJyUsernameInfo_YH(String username);

	/**
	 * 后台主页- 点击左边菜单【文章管理】异步加载数据方法
	 * @param pageModel
	 * @return
	 */
	String ajaxLoadMenu_WZ(PageModel pageModel);

	/**
	 * 后台主页- 评批量删除文章（也可以单个文章删除）Method
	 * @param articleIds
	 * @return
	 */
	String ajaxDeleteplArticleInfo_WZ(String articleIds_Str);

	/**
	 * 后台主页- 点击预览按钮时，根据articleId把该文章加载出来 Method
	 * @param articleId
	 * @return
	 */
	String ajaxLoadArticleInfo_WZ(String articleId);

	/**
	 * 后台主页- 点击添加文章按钮时，异步加载所有menu分类 Method
	 * @return
	 */
	String ajaxLoadArticleMenuIds_WZ();

	/**
	 * 后台主页- 异步上传文章的封面 Method
	 * @param article_faceimg
	 * @param article_faceimgFileName
	 * @return
	 */
	String ajaxUploadArticleFaceImg_WZ(File article_faceimg,
			String article_faceimgFileName);
	
	/**
	 * 后台主页- 异步添加文章 Method
	 * @param article_content_txt
	 * @param article_faceimg
	 * @param article_faceimgFileName
	 * @param article
	 * @param article_menuId 
	 * @return
	 */
	String ajaxAddArticleInfo_WZ(Article article);

	/**
	 * 后台主页- 根据articleId异步修改文章 Method
	 * @param article_content_txt 
	 * @param article
	 * @return
	 */
	String ajaxUpdateArticleInfo_WZ(Article article);
	
	/**
	 * 后台主页- 点击【评论管理】异步所有评论数据
	 * @param pageModel
	 * @return
	 */
	String ajaxLoadMenuRemark_PL(PageModel pageModel);
	
	/**
	 * 后台主页 - 删除评论（支持批量删除）
	 * @param remarkIds_Str
	 * @return
	 */
	String ajaxDeleteplRemarkInfo_PL(String remarkIds_Str);
	
	/**
	 * 后台主页 - 点击【菜单管理】异步加载所有菜单数据
	 * @param pageModel
	 * @return
	 */
	String ajaxLoadMenu_CD(PageModel pageModel);
	
	/**
	 * 后台主页 - 失去焦点就修改菜单名
	 * @param newNemuName
	 * @param menuId 
	 * @return
	 */
	String ajaxUpdateMenuNameInfo_CD(String newNemuName, String menuId);
	
	/**
	 * 后台主页 - 点击添加按钮 异步添加Menu
	 * @param menu
	 * @return
	 */
	String ajaxAddMenuInfo_CD(Menu menu);
	
	/**
	 * 后台主页 - 点击【链接管理】异步加载所有链接数据
	 * @param pageModel
	 * @return
	 */
	String ajaxLoadLinkInfo_LJ(PageModel pageModel);
	
	/**
	 * 失去焦点便修改链接名称
	 * @param link_name
	 * @param linkId 
	 * @return
	 */
	String ajaxUpdateLinkNameInfo_LJ(String link_name, String linkId);
	
	/**
	 * 后台主页 - 失去焦点便修改链接URL
	 * @param linkId
	 * @param link_url
	 * @return
	 */
	String ajaxUpdateLinkUrlInfo_LJ(String linkId, String link_url);

	/**
	 * 后台主页 - 添加Link
	 * @param link
	 * @return
	 */
	String ajaxAddLinkInfo_LJ(Link link);
	
	/**
	 * 后台主页 - 删除Link
	 * @param linkId
	 * @return
	 */
	String ajaxDeleteLinkInfo_LJ(String linkId);

	/** ######################################前台操作####################################################### */
	/**
	 * index.jsp获取前5条数据
	 * @return
	 */
	List<Article> qt_getFiveArticles();

	/**
	 * index.jsp获取前5个菜单
	 * @return
	 */
	List<Menu> qt_getFiveMenus();

	/**
	 * index.jsp获取所有的菜单
	 * @return
	 */
	List<Menu> qt_getAllMenus();

	/**
	 * index.jsp获取所有的链接
	 * @return
	 */
	List<Link> qt_getAllLinks();

	/**
	 * index.jsp查询博主信息
	 * @return
	 */
	User qt_getBlogMasterMess();

	/**
	 * 根据menuId分页展示相应的文章
	 * @param menuId
	 * @param pageModel
	 * @return
	 */
	List<Article> qt_getArticlesByMenuId(String menuId, PageModel pageModel);

	/**
	 * 根据articleId展示相应的文章内容 Article-User-Menu
	 * @param articleId
	 * @return
	 */
	Article qt_getArticleContentByArticleId(String articleId);

	/**
	 * 根据articleId展示相应的文章内容 Remark
	 * @param articleId
	 * @return
	 */
	List<Remark> qt_getArticleRemarksByArticleId(String articleId);

	/**
	 * 上一篇文章
	 * @param articleId
	 * @param menuId 
	 * @return
	 */
	Article qt_getPrevArticleByArticleId(String articleId);

	/**
	 * 下一篇文章
	 * @param articleId
	 * @param menuId 
	 * @return
	 */
	Article qt_getNextArticleByArticleId(String articleId);

	/**
	 * 前台异步添加评论
	 * @param remark
	 * @return
	 */
	String qt_ajaxAddRemarkInfo(Remark remark);

	/**
	 * 喜欢+1
	 * @param articleId
	 * @return
	 */
	String qt_ajaxAddLikeInfo(String articleId);

	/**
	 * 回车搜索文章
	 * @param searchContent
	 * @return
	 */
	List<ArticleSo> qt_getSearchArticles(String searchContent);
}
