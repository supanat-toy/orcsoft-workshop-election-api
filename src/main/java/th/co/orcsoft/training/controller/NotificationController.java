package th.co.orcsoft.training.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import th.co.orcsoft.training.common.db.service.NotificationService;
import th.co.orcsoft.training.controller.common.BaseController;
import th.co.orcsoft.training.model.common.AbsResponseModel;
import th.co.orcsoft.training.model.common.notification.response.GetFeedNotifications;

@RestController
@RequestMapping(value = "/api/notification")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NotificationController extends BaseController {

	@Autowired
	private NotificationService notificationService;

	@GetMapping(value = "getFeedNotifications", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody AbsResponseModel getAllMenuList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if (isInvalidToken(request, response)) {
			return null;
		}
		
		boolean isOfficer = this.isOfficerByHeader(request);
		
		GetFeedNotifications getFeedNotifications = new GetFeedNotifications();
		getFeedNotifications.setNotifications(notificationService.getNotificationList(isOfficer));

		return getFeedNotifications;
	}
}
