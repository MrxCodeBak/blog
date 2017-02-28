package com.nothing.blog.service.impl;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.access.BootstrapException;
import org.springframework.transaction.annotation.Transactional;

import com.nothing.blog.dao.index_dao.IndexArticleDao;
import com.nothing.blog.dao.pt_dao.ArticleDao;
import com.nothing.blog.dao.pt_dao.LinkDao;
import com.nothing.blog.dao.pt_dao.MenuDao;
import com.nothing.blog.dao.pt_dao.RemarkDao;
import com.nothing.blog.dao.pt_dao.UserDao;
import com.nothing.blog.domain.index_domain.ArticleSo;
import com.nothing.blog.domain.pt_domain.Article;
import com.nothing.blog.domain.pt_domain.Link;
import com.nothing.blog.domain.pt_domain.Menu;
import com.nothing.blog.domain.pt_domain.Remark;
import com.nothing.blog.domain.pt_domain.User;
import com.nothing.blog.exception.BlogException;
import com.nothing.blog.service.BlogService;
import com.nothing.blog_tools.cont.WebConstant;
import com.nothing.blog_tools.htmltotext.HtmlToText;
import com.nothing.blog_tools.page.PageModel;
import com.nothing.blog_tools.timeaxis.TimeAxis;


/**
 * 博客项目业务实现层
 * @author: Nothing
 * @email: 761328790@qq.com
 * @company: Sichuan Agricultural University
 * @date 2015年7月29日
 */
@Transactional(rollbackFor={RuntimeException.class})
public class BlogServiceImpl implements BlogService {
	/** setter 方法将Dao层注入进Service层 */
	private ArticleDao articleDao;
	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}
	private MenuDao menuDao;
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}
	private IndexArticleDao indexArticleDao;
	public void setIndexArticleDao(IndexArticleDao indexArticleDao) {
		this.indexArticleDao = indexArticleDao;
	}
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	private RemarkDao remarkDao;
	public void setRemarkDao(RemarkDao remarkDao) {
		this.remarkDao = remarkDao;
	}
	private LinkDao linkDao;
	public void setLinkDao(LinkDao linkDao) {
		this.linkDao = linkDao;
	}


	/**
	 * 异步登陆 Mthod
	 * @param username
	 * @param password
	 * @return
	 */
	@Transactional(readOnly=true)
	public String login(String username, String password) {
		try {
			JSONObject json = new JSONObject();
			User user = userDao.login(username, password);
			if (user != null && user.getPassword().equals(password)) {
				ServletActionContext.getRequest().getSession().setAttribute(WebConstant.SESSION_USER, user);
				json.put("flag", true);
			}
			user = userDao.verify_exist(username);
			if (user != null && !user.getPassword().equals(password)) {
				json.put("flag", false);
			}
			return json.toString();
		} catch (Exception e) {
			throw new BlogException("异步登陆Mthod出错！");
		}
	}


	/**
	 * 跳转到后台页面时准备数据 Mthod
	 * @return
	 */
	@Transactional(readOnly=true)
	public Map<String, Integer> readyData() {
		try {
			Map<String, Integer> count = new HashMap<>();
			count.put("userCount", userDao.find(User.class).size());
			count.put("articleCount", articleDao.find(Article.class).size());
			count.put("remarkCount", remarkDao.find(Remark.class).size());
			count.put("menuCount", menuDao.find(Menu.class).size());
			count.put("linkCount", linkDao.find(Link.class).size());
			return count;
		} catch (Exception e) {
			throw new BlogException("跳转到后台页面时准备数据Mthod出错！");
		}
	}

	/**
	 * 后台主页-异步加载【首页】所需数据 Mthod
	 * @return
	 */
	@Transactional(readOnly=true)
	public String ajaxLoadMenu_SY() {
		try {
			JSONObject json = new JSONObject();
			Map<String, Integer> count = new HashMap<>();
			count.put("userCount", userDao.find(User.class).size());
			count.put("articleCount", articleDao.find(Article.class).size());
			count.put("remarkCount", remarkDao.find(Remark.class).size());
			count.put("menuCount", menuDao.find(Menu.class).size());
			count.put("linkCount", linkDao.find(Link.class).size());
			json.put("count", count);
			return json.toString();
		} catch (Exception e) {
			throw new BlogException("后台主页-异步加载【首页】所需数据Mthod出错！");
		}
	}

	/** ###################################后台 用户管理模块################################################ */
	/**
	 * 后台主页-异步加载【用户管理】所需数据 Mthod
	 * @return
	 */
	@Transactional(readOnly=true)
	public String ajaxLoadMenu_YH(PageModel pageModel) {
		try {
			JSONObject json = new JSONObject();
			List<User> users = userDao.ajaxLoadMenu_YH(pageModel);
			json.put("pageModel", pageModel);
			json.put("users", users);
			return json.toString();
		} catch (Exception e) {
			throw new BlogException("后台主页-异步加载【用户管理】所需数据Mthod处所！");
		}
	}

	/**
	 * 后台主页-跳到修改用户页面，准备个人信息所需数据 Mthod
	 * @param userId
	 * @return
	 */
	@Transactional(readOnly=true)
	public String ajaxLoadUpdateUserInfo_YH(String userId) {
		try {
			JSONObject json = new JSONObject();
			User user = userDao.get(User.class, Integer.valueOf(userId));
			json.put("user", user);
			return json.toString();
		} catch (Exception e) {
			throw new BlogException("后台主页-跳到修改用户页面，准备个人信息所需数据 Mthod 出错！");
		}
	}

	/**
	 * 后台主页-异步修改个人信息 Mthod
	 * @param user
	 * @return
	 */
	@Transactional(readOnly=false)
	public String ajaxUpdateUserInfo_YH(User user) {
		try {
			JSONObject json = new JSONObject();
			User user_sjk = userDao.get(User.class, user.getId());
			user_sjk.setUsername(user_sjk.getUsername());
			user_sjk.setPassword(user_sjk.getPassword());
			user_sjk.setNickname(user.getNickname());
			user_sjk.setReallyname(user.getReallyname());
			user_sjk.setQq(user.getQq());
			user_sjk.setEmail(user.getEmail());
			user_sjk.setTel(user.getTel());
			user_sjk.setWebsite(user.getWebsite());
			user_sjk.setSchool(user.getSchool());
			user_sjk.setMarjor(user.getMarjor());
			user_sjk.setJob(user.getJob());
			user_sjk.setRole(user_sjk.getRole());
			return json.toString();
		} catch (Exception e) {
			throw new BlogException("后台主页-异步修改个人信息 Mthod出错！");
		}
	}

	/**
	 * 后台主页- 异步删除用户 (支持批量删除) Mthod
	 * @param userId
	 * @return
	 */
	@Transactional(readOnly=false)
	public String ajaxDeleteUserInfo_YH(String userId) {
		try {
			JSONObject json = new JSONObject();
			User user_sjk = userDao.get(User.class, Integer.valueOf(userId));
			if (user_sjk.getRole().equals("1")) {
				json.put("flag", 1);//管理员不允许删除
			}else{
				//userId的类型必须要和User类中的id属性类型要相同
				Integer[] userIds = {Integer.valueOf(userId)};
				userDao.deleteUser(userIds);
			}
			return json.toString();
		} catch (Exception e) {
			throw new BlogException("后台主页- 异步删除用户 Mthod 出错！");
		}
	}

	/**
	 * 后台主页- 异步添加用户 Mthod
	 * @param user
	 * @return
	 */
	@Transactional(readOnly=false)
	public String ajaxAddUserInfo_YH(User user) {
		try {
			JSONObject json = new JSONObject();
			//后台校验账号是否存在
			if (userDao.ajaxJyUsernameInfo_YH(user.getUsername()) != null) {
				json.put("flag", true);
			}else{
				user.setRole("2");
				user.setReallyname("隔壁老王");
				/**
				 * User类必须为id指定生成策略：@GeneratedValue(strategy=GenerationType.IDENTITY)
				 */
				userDao.save(user);
			}
			return json.toString();
		} catch (Exception e) {
			throw new BootstrapException("后台主页- 异步添加用户 Mthod");
		}
	}

	/**
	 * 后台主页 - 异步校验用户名是否已经存在
	 * @param username
	 * @return
	 */
	@Transactional(readOnly=true)
	public String ajaxJyUsernameInfo_YH(String username) {
		try {
			JSONObject json = new JSONObject();
			User user = userDao.ajaxJyUsernameInfo_YH(username);
			if (user != null) {
				json.put("flag", true);
			}
			return json.toString();
		} catch (Exception e) {
			throw new BlogException();
		}
	}

	/** ###################################后台 文章管理模块################################################ */
	/**
	 * 后台主页- 点击左边菜单【文章管理】异步加载数据方法
	 * @param pageModel
	 * @return
	 */
	@Transactional(readOnly=true)
	public String ajaxLoadMenu_WZ(PageModel pageModel) {
		try {
			JSONObject json = new JSONObject();
			List<Article> articles = articleDao.ajaxLoadMenu_WZ(pageModel);
			for (Article article : articles) {
				if (HtmlToText.htmlToText(article.getArticle_content()).trim().length() > 30) {
//					int index = 0;
//					//做点小动作
//					if (article.getArticle_content().matches (".*[\u4e00-\u9fa5].*")) {
//						index = article.getArticle_content().split ("[\u4e00-\u9fa5]")[0].length ();
//					}
					article.setArticle_content(HtmlToText.htmlToText(article.getArticle_content()).trim().substring(0, 30)+"...");
				}
				if (article.getArticle_title().length() > 10) {
					article.setArticle_title(article.getArticle_title().substring(0, 10)+"...");
				}
				//格式化json时间
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				article.setArticle_timeaxis(sdf.format(article.getArticle_fbtime()));
			}
			json.put("articles", articles);
			json.put("pageModel", pageModel);
			return json.toString();
		} catch (Exception e) {
			throw new BlogException("后台主页-异步加载【文章管理】所需数据方法出错！");
		}
	}

	/**
	 * 后台主页- 批量删除文章（也可以单个文章删除）Method
	 * @param articleIds
	 * @return
	 */
	@Transactional(readOnly=false)
	public String ajaxDeleteplArticleInfo_WZ(String articleIds_Str) {
		try {
			JSONObject json = new JSONObject();
			if (articleIds_Str.length() > 1) {
				String[] ids_Str = articleIds_Str.split(",");
				Integer[] arictleIds = new Integer[ids_Str.length];
				for (int i = 0; i < ids_Str.length; i++) {
					arictleIds[i] = Integer.valueOf(ids_Str[i]);
					//删除
					articleDao.deleteArticle(arictleIds);
					/** ---删除索引--- */
					indexArticleDao.delete(ArticleSo.class, Integer.valueOf(ids_Str[i]));
				}
			}else{
				Integer[] arictleIds = {Integer.valueOf(articleIds_Str)};
				//删除
				articleDao.deleteArticle(arictleIds);
				/** ---删除索引--- */
				indexArticleDao.delete(ArticleSo.class, Integer.valueOf(articleIds_Str));
			}
			return json.toString();
		} catch (Exception e) {
			throw new BlogException("后台主页- 评批量删除文章（也可以单个文章删除）Method 出错！");
		}
	}

	/**
	 * 后台主页- 点击预览按钮时，根据articleId把该文章加载出来 Method
	 * @param articleId
	 * @return
	 */
	@Transactional(readOnly=true)
	public String ajaxLoadArticleInfo_WZ(String articleId) {
		try {
			JSONObject json = new JSONObject();
			Article article = articleDao.get(Article.class, Integer.valueOf(articleId));
			json.put("article", article);
			return json.toString();
		} catch (Exception e) {
			throw new BlogException("后台主页- 点击预览按钮时，根据articleId把该文章加载出来 Method 出错！");
		}
	}

	/**
	 * 后台主页- 点击添加文章按钮时，异步加载所有menu分类 Method
	 * @return
	 */
	@Transactional(readOnly=true)
	public String ajaxLoadArticleMenuIds_WZ() {
		try {
			JSONObject json = new JSONObject();
			List<Menu> menus = menuDao.find(Menu.class);
			json.put("menus", menus);
			return json.toString();
		} catch (Exception e) {
			throw new BlogException("后台主页- 点击添加文章按钮时，异步加载所有menu分类 Method出错！");
		}
	}

	/**
	 * 后台主页- 异步上传文章的封面 Method
	 * @param articleFaceImg
	 * @param articleFaceImgFileName
	 * @return
	 */
	@Transactional(readOnly=false)
	public String ajaxUploadArticleFaceImg_WZ(File article_faceimg,
			String article_faceimgFileName) {
		try {
			JSONObject json = new JSONObject();
			//现查询数据库Article表中的所有封面的url，再循环的在上传目录去查，如果存在则不删除，不存在则删除，避免垃圾文件
			List<Article> articles = articleDao.find(Article.class);
			//找到存储路径
			String absolutelyPath = ServletActionContext.getServletContext().getRealPath("/");
			String ccPath = absolutelyPath+File.separator+"images"+File.separator+"AllUploadImgs"+File.separator+"ArticleFaceImgs";
			File file = new File(ccPath);
			if (file.isDirectory()) {
				File[] files = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					boolean deleteFlag = false;
					for (Article article : articles) {
						//若在数据库不存在该文件名
						if (!files[i].getName().equals(article.getArticle_faceimg())) {
							deleteFlag = true;
						}else{
							deleteFlag = false;
							break;
						}
					}
					if (deleteFlag == true) {
						files[i].delete();
					}
				}
			}
			//正式上传
			String newFileName = UUID.randomUUID().toString()+"."+FilenameUtils.getExtension(article_faceimgFileName);
			FileUtils.copyFile(article_faceimg, new File(ccPath+File.separator+newFileName));
			
			json.put("newFileName", newFileName);
			return json.toString();
		} catch (Exception e) {
			throw new BlogException("后台主页- 异步上传文章的封面 Method");
		}
	}
	
	/**
	 * 后台主页- 异步添加文章 Method
	 * @param article_content_txt
	 * @param article_faceimg
	 * @param article_faceimgFileName
	 * @param article
	 * @return
	 */
	@Transactional(readOnly=false)
	public String ajaxAddArticleInfo_WZ(Article article) {
		try {
			JSONObject json = new JSONObject();
			User user = (User)ServletActionContext.getRequest().getSession().getAttribute(WebConstant.SESSION_USER);
			if (user != null) {
				article.setArticle_fbtime(new Date());
				article.setArticle_like(String.valueOf(0));
				article.setArticle_timeaxis("刚刚");
				article.setArticle_remarknum("0");
				Integer id =  (Integer) articleDao.save(article);
				//添加in索引库
				if (id > 0) {
					ArticleSo so = new ArticleSo();
					so.setId(id);
					so.setArticle_title(HtmlToText.htmlToText(article.getArticle_title()).trim());
					so.setArticle_content(HtmlToText.htmlToText(article.getArticle_content()).trim());
					indexArticleDao.save(so);
				}
			}
			return json.toString();
		} catch (Exception e) {
			throw new BlogException("后台主页- 异步添加文章 Method 出错！");
		}
	}

	/**
	 * 后台主页- 根据articleId异步修改文章 Method
	 * @param article
	 * @return
	 */
	@Transactional(readOnly=false)
	public String ajaxUpdateArticleInfo_WZ(Article article) {
		try {
			JSONObject json = new JSONObject();
			Article article_sjk = articleDao.get(Article.class, Integer.valueOf(article.getId()));
			/** ------修改--------- */
			System.out.println("article.getArticle_faceimg()--->"+article.getArticle_faceimg());
			article_sjk.setArticle_title(article.getArticle_title());
			article_sjk.setArticle_content(article.getArticle_content());
			article_sjk.setArticle_fbtime(article_sjk.getArticle_fbtime());
			if (article.getArticle_faceimg() == null || article.getArticle_faceimg().equals("")) {
				System.out.println("----用户未更改封面---");
				article_sjk.setArticle_faceimg(article_sjk.getArticle_faceimg());
			}else{
				System.out.println("----用户进行了更改封面---");
				article_sjk.setArticle_faceimg(article.getArticle_faceimg());
			}
			article_sjk.setMenu(article.getMenu());
			
			//修改缩影
			ArticleSo so = indexArticleDao.get(ArticleSo.class, Integer.valueOf(article.getId()));
			so.setId(Integer.valueOf(article.getId()));
			so.setArticle_title(article.getArticle_title());
			so.setArticle_content(article.getArticle_content());
			indexArticleDao.update(so);
			return json.toString();
		} catch (Exception e) {
			throw new BlogException("后台主页- 根据articleId异步修改文章 Method 出错！");
			
		}
	}

	/** ###################################后台 评论管理模块################################################ */
	/**
	 * 后台主页- 点击【评论管理】异步所有评论数据
	 * @param pageModel
	 * @return
	 */
	@Transactional(readOnly=true)
	public String ajaxLoadMenuRemark_PL(PageModel pageModel) {
		try {
			JSONObject json = new JSONObject();
			List<Remark> remarks = remarkDao.ajaxLoadMenuRemark_PL(pageModel);
			for (Remark remark : remarks) {
				if (HtmlToText.htmlToText(remark.getRemark_content()).trim().length() >30) {
					remark.setRemark_content(HtmlToText.htmlToText(remark.getRemark_content()).trim().substring(0, 30)+"...");
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				remark.setRemark_timejson(sdf.format(remark.getRemark_time()));
				remark.setRemark_timeaxis(TimeAxis.timeFormatText(remark.getRemark_time()));
			}
			json.put("pageModel", pageModel);
			json.put("remarks", remarks);
			return json.toString();
		} catch (Exception e) {
			throw new BlogException("后台主页- 点击【评论管理】异步所有评论数据出错！");
		}
	}
	
	/**
	 * 后台主页 - 删除评论（支持批量删除）
	 * @param remarkIds_Str
	 * @return
	 */
	@Transactional(readOnly=false)
	public String ajaxDeleteplRemarkInfo_PL(String remarkIds_Str) {
		try {
			JSONObject json = new JSONObject();
			if (remarkIds_Str.length() > 1) {
				String[] ids_Str = remarkIds_Str.split(",");
				Integer[] remarkIds = new Integer[ids_Str.length];
				for (int i = 0; i < ids_Str.length; i++) {
					remarkIds[i] = Integer.valueOf(ids_Str[i]);
				}
				remarkDao.deleteRemark(remarkIds);
			}else{
				Integer[] remarkIds = {Integer.valueOf(remarkIds_Str)};
				remarkDao.deleteRemark(remarkIds);
			}
			return json.toString();
		} catch (Exception e) {
			throw new BlogException("后台主页 - 删除评论（支持批量删除）");
		}
	}
	
	/** ###################################后台 菜单管理模块################################################ */
	/**
	 * 后台主页 - 点击【菜单管理】异步加载所有菜单数据
	 * @param pageModel
	 * @return
	 */
	@Transactional(readOnly=true)
	public String ajaxLoadMenu_CD(PageModel pageModel) {
		try {
			JSONObject json = new JSONObject();
			List<Menu> menus = menuDao.ajaxLoadMenu_CD(pageModel);
			List<Integer> articleNum = new ArrayList<>();
			for (Menu menu : menus) {
				articleNum.add(menuDao.getArticleCount(menu.getId()));
			}
			json.put("pageModel", pageModel);
			json.put("articleNum", articleNum);
			json.put("menus", menus);
			return json.toString();
		} catch (Exception e) {
			throw new BlogException("后台主页 - 点击【菜单管理】异步加载所有菜单数据出错！");
		}
	}
	
	/**
	 * 后台主页 - 失去焦点就修改菜单名
	 * @param newNemuName
	 * @return
	 */
	@Transactional(readOnly=false)
	public String ajaxUpdateMenuNameInfo_CD(String newNemuName, String menuId) {
		try {
			System.out.println("menuId:"+menuId);
			System.out.println("newNemuName:"+newNemuName);
			JSONObject json = new JSONObject();
			Menu menu = new Menu();
			menu.setId(Integer.valueOf(menuId));
			menu.setMenu_name(newNemuName);
			menuDao.update(menu);
			return json.toString();
		} catch (Exception e) {
			throw new BlogException("后台主页 - 失去焦点就修改菜单名出错！");
		}
	}
	
	/**
	 * 后台主页 - 点击添加按钮 异步添加Menu
	 * @param menu
	 * @return
	 */
	@Transactional(readOnly=false)
	public String ajaxAddMenuInfo_CD(Menu menu) {
		try {
			JSONObject json = new JSONObject();
			menuDao.save(menu);
			return json.toString();
		} catch (Exception e) {
			throw new BlogException("后台主页 - 点击添加按钮 异步添加Menu 出错！");
		}
	}
	
	/** ###################################后台 链接管理模块################################################ */
	/**
	 * 后台主页 - 点击【链接管理】异步加载所有链接数据
	 * @param pageModel
	 * @return
	 */
	@Transactional(readOnly=true)
	public String ajaxLoadLinkInfo_LJ(PageModel pageModel) {
		try {
			JSONObject json = new JSONObject();
			List<Link> links = linkDao.ajaxLoadLinkInfo_LJ(pageModel);
			json.put("links", links);
			json.put("pageModel", pageModel);
			return json.toString();
		} catch (Exception e) {
			throw new BlogException("后台主页 - 点击【链接管理】异步加载所有链接数据出错！");
		}
	}
	
	/**
	 * 失去焦点便修改链接名称
	 * @param link_name
	 * @return
	 */
	@Transactional(readOnly=false)
	public String ajaxUpdateLinkNameInfo_LJ(String link_name, String linkId) {
		try {
			JSONObject json = new JSONObject();
			Link link = linkDao.get(Link.class, Integer.valueOf(linkId));
			
			link.setLink_url(link.getLink_url());
			link.setLink_name(link_name);
			return json.toString();
		} catch (Exception e) {
			throw new BlogException("失去焦点便修改链接名称出错！");
		}
	}
	
	/**
	 * 后台主页 - 失去焦点便修改链接URL
	 * @param linkId
	 * @param link_url
	 * @return
	 */
	@Transactional(readOnly=false)
	public String ajaxUpdateLinkUrlInfo_LJ(String linkId, String link_url) {
		try {
			JSONObject json = new JSONObject();
			Link link = linkDao.get(Link.class, Integer.valueOf(linkId));
			link.setLink_name(link.getLink_name());
			link.setLink_url(link_url);
			return json.toString();
		} catch (Exception e) {
			throw new BlogException("失去焦点便修改链接URL出错！");
		}
	}
	
	/**
	 * 后台主页 - 添加Link
	 * @param link
	 * @return
	 */
	@Transactional(readOnly=false)
	public String ajaxAddLinkInfo_LJ(Link link) {
		try {
			JSONObject json = new JSONObject();
			linkDao.save(link);
			return json.toString();
		} catch (Exception e) {
			throw new BlogException("后台主页 - 添加Link出错！");			
		}
	}
	
	/**
	 * 后台主页 - 删除Link
	 * @param linkId
	 * @return
	 */
	@Transactional(readOnly=false)
	public String ajaxDeleteLinkInfo_LJ(String linkId) {
		try {
			JSONObject json = new JSONObject();
			Link link = new Link();
			link.setId(Integer.valueOf(linkId));
			linkDao.delete(link);
			return json.toString();
		} catch (Exception e) {
			throw new BlogException("后台主页 - 删除Link出错！");
		}
	}
	
	/** ######################################前台操作####################################################### */
	/**
	 * index.jsp获取前5条数据
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Article> qt_getFiveArticles() {
		try {
			List<Article> articles_sjk = articleDao.qt_getFiveArticles();
			for (Article article : articles_sjk) {
				if (HtmlToText.htmlToText(article.getArticle_content()).trim().length() > 120) {
					article.setArticle_content("<font style='font-family:微软雅黑'>"+HtmlToText.htmlToText(article.getArticle_content()).trim().substring(0, 120)+"...</font>");
				}else{
					article.setArticle_content("<font style='font-family:微软雅黑'>"+HtmlToText.htmlToText(article.getArticle_content()).trim()+"</font>");
				}
				//时间格式化
				article.setArticle_timeaxis((TimeAxis.timeFormatText(article.getArticle_fbtime())));
				//评论数量
				int num = remarkDao.getRemarkCountByArticleId(article.getId());
				article.setArticle_remarknum(String.valueOf(num));
			}
			return articles_sjk;
		} catch (Exception e) {
			throw new BlogException("index.jsp获取前5条数据 出错！");
		}
	}

	/**
	 * index.jsp获取前5个菜单
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Menu> qt_getFiveMenus() {
		try {
			return menuDao.qt_getFiveMenus();
		} catch (Exception e) {
			throw new BlogException("index.jsp获取前5个菜单出错！");
		}
	}

	/**
	 * index.jsp获取左右的菜单
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Menu> qt_getAllMenus() {
		try {
			return menuDao.find(Menu.class);
		} catch (Exception e) {
			throw new BlogException("index.jsp获取所有的菜单出错！");
		}
	}

	/**
	 * index.jsp获取所有的链接
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Link> qt_getAllLinks() {
		try {
			return menuDao.find(Link.class);
		} catch (Exception e) {
			throw new BlogException("index.jsp获取所有的链接出错！");
		}
	}

	/**
	 * index.jsp查询博主信息
	 * @return
	 */
	@Transactional(readOnly=true)
	public User qt_getBlogMasterMess() {
		try {
			return userDao.get(User.class, 1);
		} catch (Exception e) {
			throw new BlogException("index.jsp查询博主信息出错！");
		}
	}

	/**
	 * 根据menuId分页展示相应的文章
	 * @param menuId
	 * @param pageModel
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Article> qt_getArticlesByMenuId(String menuId,
			PageModel pageModel) {
		try {
			List<Article> articles = articleDao.getArticlesByMenuId(menuId, pageModel);
			for (Article article : articles) {
				if (HtmlToText.htmlToText(article.getArticle_content()).trim().length() > 120) {
					article.setArticle_content("<font style='font-family:微软雅黑'>"+HtmlToText.htmlToText(article.getArticle_content()).trim().substring(0, 120)+"...</font>");
				}else{
					article.setArticle_content("<font style='font-family:微软雅黑'>"+HtmlToText.htmlToText(article.getArticle_content()).trim()+"</font>");
				}
				//时间格式化
				article.setArticle_timeaxis((TimeAxis.timeFormatText(article.getArticle_fbtime())));
				//评论数量
				int num = remarkDao.getRemarkCountByArticleId(article.getId());
				article.setArticle_remarknum(String.valueOf(num));
			}
			return articles;
		} catch (Exception e) {
			throw new BlogException("根据menuId分页展示相应的文章出错！");
		}
	}

	/**
	 * 根据articleId展示相应的文章内容  Article-User-Menu
	 * @param articleId
	 * @return
	 */
	@Transactional(readOnly=true)
	public Article qt_getArticleContentByArticleId(String articleId) {
		try {
			Article article = articleDao.getArticleContentByArticleId(articleId);
			//时间格式化
			article.setArticle_timeaxis((TimeAxis.timeFormatText(article.getArticle_fbtime())));
			//评论数量
			int num = remarkDao.getRemarkCountByArticleId(article.getId());
			article.setArticle_remarknum(String.valueOf(num));
			return article;
		} catch (Exception e) {
			throw new BlogException("根据articleId展示相应的文章内容出错！");
		}
	}


	/**
	 * 根据articleId展示相应的文章内容 Remark
	 * @param articleId
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Remark> qt_getArticleRemarksByArticleId(String articleId) {
		try {
			List<Remark> remarks = remarkDao.getArticleRemarksByArticleId(articleId);
			for (Remark remark : remarks) {
				//时间格式化
				remark.setRemark_timeaxis(TimeAxis.timeFormatText(remark.getRemark_time()));
			}
			return remarks;
		} catch (Exception e) {
			throw new BlogException("根据articleId展示相应的文章内容 Remark出错！");
		}
	}

	/**
	 * 上一篇文章
	 * @param articleId
	 * @param menuId
	 * @return
	 */
	@Transactional(readOnly=true)
	public Article qt_getPrevArticleByArticleId(String articleId) {
		try {
			int num = 1;
			Article article = articleDao.get(Article.class, Integer.valueOf(articleId)-num);
			int maxId = articleDao.getMaxId();
			for (int i = 0; i < maxId; i++) {
				if (article == null) {
					if ((Integer.valueOf(articleId)-num) == 0) {
						article = new Article();
						article.setArticle_title("");
						break;
					}else{
						num++;
						article = articleDao.get(Article.class, Integer.valueOf(articleId)-num);
					}
				}else{
					break;
				} 
			}
			return article;
		} catch (Exception e) {
			throw new BlogException("查询上一篇文章时出错！");
		}
	}

	/**
	 * 下一篇文章
	 * @param articleId
	 * @param menuId 
	 * @return
	 */
	@Transactional(readOnly=true)
	public Article qt_getNextArticleByArticleId(String articleId) {
		try {
			int num = 1;
			Article article = articleDao.get(Article.class, Integer.valueOf(articleId)+num);
			int maxId = articleDao.getMaxId();
			for (int i = 0; i < maxId; i++) {
				if (article == null) {
					num++;
					if ((Integer.valueOf(articleId)+num) == maxId) {
						article.setArticle_title("");
						break;
					}else{
						article = articleDao.get(Article.class, Integer.valueOf(articleId)+num);
					}
				}else{
					break;
				}
			}
			return article;
		} catch (Exception e) {
			throw new BlogException("查询下一篇文章时出错！");
		}
	}

	/**
	 * 前台异步添加评论
	 * @param remark
	 * @return
	 */
	@Transactional(readOnly=false)
	public String qt_ajaxAddRemarkInfo(Remark remark) {
		try {
			JSONObject json = new JSONObject();
			remark.setRemark_time(new Date());
			Integer id = (Integer) remarkDao.save(remark);
			Remark newRemark = remarkDao.get(Remark.class, id);
			//格式化日期
			newRemark.setRemark_timeaxis(TimeAxis.timeFormatText(newRemark.getRemark_time()));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			newRemark.setRemark_timejson(sdf.format(newRemark.getRemark_time()));
			json.put("newRemark", newRemark);
			return json.toString();
		} catch (Exception e) {
			throw new BlogException("前台异步添加评论出错！");
		}
	}

	/**
	 * 喜欢+1
	 * @param articleId
	 * @return
	 */
	@Transactional(readOnly=false)
	public String qt_ajaxAddLikeInfo(String articleId) {
		try {
			JSONObject json = new JSONObject();
			Article article = articleDao.get(Article.class, Integer.valueOf(articleId));
			article.setArticle_like(String.valueOf((Integer.valueOf(article.getArticle_like())+1)));
			json.put("likeNum", Integer.valueOf(article.getArticle_like()));
			return json.toString();
		} catch (Exception e) {
			throw new BlogException("喜欢+1出错！");
		}
	}


	/**
	 * 回车搜索文章
	 * @param searchContent
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<ArticleSo> qt_getSearchArticles(String searchContent) {
		try {
			return indexArticleDao.highLightFind("article_title:"+searchContent+ " OR article_content:"+searchContent, new Object[]{"article_title", "article_content"});
		} catch (Exception e) {
			throw new BlogException("回车搜索文章出错！");
		}
	}
}

