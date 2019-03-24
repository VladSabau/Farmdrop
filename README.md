# Farmdrop
The main goal of this test is to evaluate your coding skills in a professional context. For that, we ask you to create a small app that displays a list of Farmdrop producers.

Displaying a list of producers

Use the following API endpoint (GET) to fetch a list of producers

Use this endpoint: https://api.farmdrop.com/2/producers

No authentication required
Response will contain a JSON object.

Parse the JSON object to produce a smooth scrolling list of producers. Each cell should at least contain:

The producer's name (JSON key name).
The producer's short description (short_description). Truncate it, if the length is more than ~3 / 4 lines long.
The producer's first image (images array)


Add pagination to your list of producers created as part of task A. You can use the same endpoint, and pass the following query parameters:


page: The page number (first page is 1).

per_page_limit: The size of the page.

NB: When there are no more pages, the pagination object will no longer have a next value, or the response will be empty.

Persist the list of producers on the device for viewing offline.
Make sure that the list is kept up to date with the server data when available.

Requirements

Submit as an Android Studio project.
You can use make use of any libraries or frameworks to organise the architecture and/or reduce "boilerplate" code.


Hints

We use an MVP architecture with RxJava and Dagger, to help us write code that makes sense and is easy to test. We also prefer to use Kotlin for new features.


Evaluation criteria:

Quality is better than quantity
Use of SDK versions compatible with Google Play
Device orientation handling is not required, although we expect you to structure your code to be able to easily add support for this
Compatibility and good use of additional libraries.
Use of new components and/or Material Design.
Clean and structured code
Use of design patterns
Unit tests are considered extras
