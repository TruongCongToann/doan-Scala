//package services.post
//
//import bbs.posts.models.{Post, PostID}
//import bbs.repository.post.PostRepository
//import bbs.services.post.PostServices
//import posts.models.Post
//import org.joda.time.DateTime
//import org.scalatest.FunSuite
//import org.specs2.main.ArgumentsShortcuts.sequential
//import org.specs2.mock.Mockito
//
//import java.sql.SQLDataException
//import scala.util.{Failure, Success, Try}
//
//class PostServicesTest extends FunSuite with Mockito {
//
//   sequential
//
//   // *** Data ***
//   private lazy val post1 = Post(PostID(1), "Post 1", "https://image.abc.xyz", "lorem bla bla", None, None)
//   private lazy val post2 = Post(
//      PostID(2),
//      "Post 2",
//      "https://image2.abc.xyz",
//      "lorem blo blo",
//      Some(DateTime.now()),
//      Some(DateTime.now())
//   )
//
//   private lazy val post3 = Post(
//      PostID(3),
//      "Post 3",
//      "https://image2.abc.xyz",
//      "lorem blo blo",
//      Some(DateTime.now()),
//      Some(DateTime.now())
//   )
//
//   private lazy val post4 = Post(
//      PostID(4),
//      "Post 4",
//      "https://image2.abc.xyz",
//      "lorem blo blo",
//      Some(DateTime.now()),
//      Some(DateTime.now())
//   )
//
//   private lazy val post5 = Post(
//      PostID(5),
//      "Post 5",
//      "https://image2.abc.xyz",
//      "lorem blo blo",
//      Some(DateTime.now()),
//      Some(DateTime.now())
//   )
//
//   private lazy val post6 = Post(
//      PostID(6),
//      "Post 6",
//      "https://image2.abc.xyz",
//      "lorem blo blo",
//      Some(DateTime.now()),
//      Some(DateTime.now())
//   )
//   private lazy val posts = List(post1, post2, post3, post4, post5, post6)
//   private lazy val postsPaginated = List(post1, post2, post3)
//
//   // *** Environment ***
//   private val postRepository = mock[PostRepository]
//   private val exception = mock[java.lang.Exception]
//   private val postService = new PostServices(postRepository)
//
//   test("should return all posts") {
//      postRepository.getAllPosts returns Success(posts)
//      val result: Try[Seq[Post]] = postService.getAllPost
//      assert(result == Success(posts))
//   }
//
//   test("should return all posts with pagination") {
//      postRepository.getAllPostsWithPagination(limit = 3, page = 1) returns Success(postsPaginated)
//      val result = postService.getPostWithPagination(3, 1)
//      assert(result == Success(postsPaginated))
//
//   }
//
//   test("should return post by existed ID") {
//      postRepository.getPostById(1) returns Success(post1)
//      val result = postService.getPostById(1)
//      assert(result == Success(post1))
//   }
//
//   test("Should throw BBSException if cannot find post by id"){
////      val nonExistedID = 1000
////      val result = postService.getPostById(nonExistedID) returns  Failure(new SQLDataException("Post Not Found"))
////      assert(result ==  Failure(exception))
//
//   }
//
//}
