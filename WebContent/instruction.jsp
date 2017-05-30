<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0" />
<title>Home | Feedback System</title>

<script src="javaScript/jQuery-2.1.4.min.js"></script>
<script src="javaScript/bootstrap.min.js"></script>
<script src="javaScript/select2/select2.full.min.js"></script>
<link rel="stylesheet" href="css/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="css/select2.min.css">
<link rel="stylesheet" href="css/AdminLTE.min.css">

<style type="text/css">
.para {
	text-indent: 3em;
	text-align: justify;
	text-justify: inter-word;
}
</style>
</head>

<body>
	<%@ include file="header.jsp"%>

	<div class="section white" id="index-banner">
		<div class="container">
			<div class="row">
				<div class="col s12 m12">
					<div class="card blue lighten-3 hoverable">
						<div class="card-content black-text">
							<h4 class="center bold">Course Evaluation Questionnaire</h4>
							<h5 class="card-title center">
								<i>The Philosophy </i>
							</h5>
							<p class="para">The primary purpose of evaluating a course is
								to qualitatively measure the effectiveness of pedagogy; and
								teaching effectiveness should be judged by the quality and
								extent of student learning. While many different teaching styles
								and methods are likely to be effective, all scholarly activities
								that focus on improving teaching and learning should be
								recognized and rewarded as a bona fide scholarly endeavor and
								accorded the types of institutional support aimed at improving
								teaching generally.</p>
							<br />
							<p class="para">Valid summative assessments of teaching
								should include student self-evaluations, student-teacher
								interactions, course evaluations, peer reviews and teaching
								portfolios. Such assessments should be designed to provide fair
								and objective information to aid faculty in the improvement of
								their teaching. Building consensus among faculty in this regard,
								providing necessary resources, and relying on the best available
								research on teaching, learning, and measurement are critical for
								this approach to evaluation.</p>
							<br />
							<p class="para">The above ideas guided our endeavor in
								adapting and framing the following set of questions towards what
								we call course evaluation.</p>
							<br />
							<p class="para">Your response to the questionnaire below will
								help in making teaching an effective activity. Therefore, we
								appreciate your honest feedback. Given below are
								questions/statements to which you should indicate your preferred
								choices according to the following scales:</p>
							<br />
							<p class="para">The scale is the following:</p>
							<p class="para">
								<b>5- Strongly Agree, 4- Agree, 3- Neutral, 2- Disagree, 1- Strongly Disagree</b>
							</p>
							<br /> <br />
						</div>
						<div class="card-action center">
							<a href="feedbackIndex.jsp"
								class="btn waves-effect waves-light blue accent-4 hoverable white-text">Lets
								Start</a>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	<%@include file="userFooter.jsp"%>
</body>
</html>
