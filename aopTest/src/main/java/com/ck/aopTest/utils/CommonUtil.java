package com.ck.aopTest.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author 杨坚
 * @version [版本号, 2016年2月19日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class CommonUtil {

	private static PropertiesUtil proper = null;

	private static Logger logger = Logger.getLogger(CommonUtil.class);

	/**
	 * 返回前台分页信息
	 * 
	 * @param <T>
	 * 
	 * @param request
	 * @param response
	 * @param parameter
	 *            前台条件参数
	 * @param reqfield
	 *            返回前台request字段
	 * @param lists
	 *            返回前台信息
	 * @param count
	 *            总记录数
	 * @param reqcondit
	 *            返回条件
	 * @param map
	 *            分页信息
	 */
	public static <T> void commonRequest(HttpServletRequest request, HttpServletResponse response, String reqfield,
			List<T> lists, int count, String pageNum) {
		request.setAttribute("totalCount", count);
		request.setAttribute(reqfield, lists);
		request.setAttribute("targetType", "navTab");
		request.setAttribute("numPerPage", "20");
		request.setAttribute("pageNum", pageNum);
	}

	// 加载资源文件
	static {
		try {
			proper = new PropertiesUtil();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 返回信息
	 * 
	 * @param flag
	 *            true输出信息
	 * @param response
	 * @param outcome
	 */
	public static void printPlatformMsg(boolean flag, HttpServletResponse response, JSONObject outcome) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw;
		try {
			if (flag) {
				logger.info("<<返回平台参数>>===========>" + outcome);
			}
			pw = response.getWriter();
			pw.write(outcome.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			logger.error("response wirter fail!", e);
		}
	}

	public static void printPlatformMsg(HttpServletResponse response, String msg) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw;
		try {
			pw = response.getWriter();
			pw.write(msg);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			logger.error("response wirter fail!", e);
		}
	}
	
	public static void printMsgArray(HttpServletResponse response, JSONArray msg) {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw;
		try {
			pw = response.getWriter();
			pw.write(msg.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			logger.error("response wirter fail!", e);
		}
	}

	/**
	 * 分页计算
	 * 
	 * @param count
	 *            总统计
	 * @param page
	 *            每页信息数
	 * @return
	 * @author 杨坚
	 * @Time 2016年12月27日
	 * @version 1.0v
	 */
	public static int reckonPage(int count) {
		int page = 10;
		// 总页数
		int totalPage = 0;
		if (count % page > 0) {
			totalPage = count / page + 1;
		} else {
			totalPage = count / page;
		}
		return totalPage;
	}

	/**
	 * 获取请求参数(List)
	 * 
	 * @param propertiesKey
	 * @return
	 * @throws Exception
	 * @author 杨坚
	 * @Time 2016年12月28日
	 * @version 1.0v
	 */
	public static JSONObject printPlatformPropertiesList(List<String> propertiesKey) throws Exception {
		// 返回信息
		JSONObject params = new JSONObject();
		for (String key : propertiesKey) {
			params.put(key, proper.readValue(key));
		}
		return params;
	}

	/**
	 * 获取请求参数(字符串数组)
	 * 
	 * @param arrays
	 * @return
	 * @throws Exception
	 * @author 杨坚
	 * @Time 2016年12月28日
	 * @version 1.0v
	 */
	public static JSONObject printPlatformPropertiesList(String[] arrays) throws Exception {
		// 返回信息
		JSONObject params = new JSONObject();
		for (String key : arrays) {
			params.put(key, proper.readValue(key));
		}
		return params;
	}

	/**
	 * 从资源文件获取信息 单条
	 * 
	 * @param propertiesKey
	 * @return
	 * @throws Exception
	 * @author 杨坚
	 * @Time 2016年12月28日
	 * @version 1.0v
	 */
	public static String printPlatformProperties(String propertiesKey) {
		try {
			return proper.readValue(propertiesKey);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 平台返回参数
	 * 
	 * @author 杨坚
	 * @Time 2016年11月11日
	 * @param errCode
	 *            返回码
	 * @return
	 * @throws Exception
	 */
	public static JSONObject printPlatform(Integer errCode) throws Exception {
		JSONObject result = new JSONObject();
		result.put("result", errCode);
		result.put("msg", proper.readValue(String.valueOf(errCode)));
		return result;
	}

	/**
	 * 分页工具
	 * 
	 * @param request
	 * @return
	 * @author 杨坚
	 * @Time 2017年2月4日
	 * @version 1.0v
	 */
	public static int checkPages(HttpServletRequest request) {
		int pageNum = 1; // 起始页
		int numPerPage = 10;// 每条数
		// 如果前台传过来的参数中有值 则使用前台参数值 否则使用默认值
		if (!(request.getParameter("numPerPage") == null || "".equals(request.getParameter("numPerPage")))) {
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		}
		if (!(request.getParameter("pageNum") == null || "".equals(request.getParameter("pageNum")))) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		// 配置开始和停止条数
		return ((pageNum - 1) * numPerPage);
	}

	/**
	 * 返回前台信息
	 * 
	 * @param flag
	 *            true为输出内容
	 * @param request
	 * @param response
	 * @param outcome
	 *            数据参数
	 * @throws Exception
	 */
	public static void printEncryptMsg(boolean flag, HttpServletRequest request, HttpServletResponse response,
			JSONObject outcome) throws Exception {
		try {
			if (flag) {
				logger.info("<<返回平台参数>>===========>" + outcome);
			}
			String en_jpCallbackId = request.getParameter("_jpCallbackId");
			PrintWriter pw = response.getWriter();
			outcome.put("TSR_RESULT", "0");
			pw.write("_jsonpCallback('" + outcome.toString() + "','" + en_jpCallbackId + "')");
			pw.flush();
			pw.close();
		} catch (IOException e) {
			logger.error("response wirter fail!", e);
		}
	}
	

	  public static void main(String[] args) throws Exception {
		String paramsss = "%7B%22timestamp%22%3A1486955646275%2C%22currentPage%22%3A1%2C%22keyword%22%3A%22%25%22%2C%22authorizeKey%22%3A%2239d34fddc1a74073a46a16598dd726ea%22%2C%22sign%22%3A%2216FABE7B56E6877EFEFE8BEBD082BC2D%22%7D";
		JSONObject params = JSONObject.fromObject(URLDecoder.decode(paramsss, "UTF-8"));
		System.out.println(params);
	}

}
