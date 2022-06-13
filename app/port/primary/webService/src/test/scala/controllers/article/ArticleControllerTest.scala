//package controllers.post
//
//import bbs.controllers.post.PostController
//import bbs.posts.models.{Post, PostID}
//import bbs.services.post.PostServices
//import org.joda.time.DateTime
//import org.specs2.mock.Mockito
//import play.api.mvc.{AnyContentAsEmpty, Result}
//import play.api.test.{FakeRequest, Helpers, PlaySpecification, WithApplication}
//
//import scala.concurrent.Future
//import scala.util.Success
//
//class PostControllerTest extends PlaySpecification with Mockito {
//
//   // execute this test sequentially
//   sequential
//
//   private lazy val post1 = Post(PostID(1), "Post 1", "https://image.abc.xyz", "lorem bla bla", None, None)
//   private lazy val post2 = Post(
//      PostID(2),
//      "Post 2",
//      "https://image2.abc.xyz",
//      "lorem blo blo",
//      Some(DateTime.now()),
//      Some(DateTime.now())
//   )
//   private lazy val posts = Seq(post1, post2)
//   private lazy val expectedPostJSON = s"{ \"id\": ${post1.id.value}, \"title\": ${post1.title}," +
//      s"\"thumbnail\": ${post1.thumbnail}," +
//      s"\"content\": ${post1.content}," +
//      s"\"createdDate\": ${post1.createdDate}," +
//      s"\"updatedDate\": ${post1.updatedDate} }"
//   private val mockedPostService = mock[PostServices]
//   private val postController = new PostController(
//      postService = mockedPostService,
//      cc = Helpers.stubControllerComponents()
//   )
//
//   "PostController" should {
//      "Get one post by ID works perfectly" >> new WithApplication() {
//
//         val httpRequest: FakeRequest[AnyContentAsEmpty.type] =
//            FakeRequest(GET, "/bbs/posts/1")
//
//         mockedPostService.getPostById(1)
//            .returns(Success(post1))
//
//         val result: Future[Result] = postController.getPostById(1)
//            .apply(httpRequest)
//
//         status(result) must equalTo(OK)
//      }
//
//      "Posts pagination works perfectly" >> new WithApplication() {
//         val request: FakeRequest[AnyContentAsEmpty.type] =
//            FakeRequest(GET, "/bbs/posts?limit=3&page=1")
//
//         mockedPostService.getAllPost returns (Success(posts))
//
//         mockedPostService.getPostWithPagination(limit = 3, page = 1)
//            .returns(
//               Success(
//                  posts
//               )
//            )
//
//         val result: Future[Result] =
//            postController.getAllWithPagination(3, 1)
//               .apply(request)
//
//         status(result) must equalTo(OK)
//      }
//   }
//
//
//}
