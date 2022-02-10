# A Simple Test Project: MyBookShop
MyBookShop is an small simple android test project. In this project we have:
- Kotlin as programming language
- Retrofit2 and Okhttp3 to get data from API
- API link: https://demo.api-platform.com/docs
- Using MVVM pattern
    - book.kt as DataModel of Book
    - MainActivityViewModel as ViewModell
    - Endless Recyclerview in MainActivity as View of Books
# Feature in this Application
In this first step, we have:
- View all books exist in the API (Using getBookList and display in Endless RecyclerView regardless of paging)
- Display book detail (Using searchBook and display in a new Activity)
# Limitation
Because time constraint, we have:
- Get booklist and book detail only. There are a lot of thing we should do such as get review list, get top book
- Basic UI. All data is in list and basic form. We should design more elegent ways to illustrate information
# Future plan
- Post data with API (We want to provide feature that we can update book, delete book and general book cover)
- Review section (Review List of each book, rating and moderate space for review each book)
