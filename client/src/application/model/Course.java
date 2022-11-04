package application.model;

import javafx.scene.paint.Color;

public class Course {
	private String courseName;
	private String courseId;
	private Color courseBackground;
	
	public String getCourseName() {
		return courseName;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public String getCourseId() {
		return courseId;
	}
	
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
//	public Color getCourseBackground() {
//		return courseBackground;
//	}
//	
//	public void setCourseBackground(Color courseBackground) {
//		this.courseBackground = courseBackground;
//	}
}
