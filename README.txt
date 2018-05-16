Image Gallery, by Ioannis Mittas

The user can select a folder with images and the images are then displayed in a grid. If the user clicks
on an image from the grid, a new screen is opened with the full sized image. The user then can scroll through
the full sized images or go back to the grid.

The app is built using Model-View-ViewModel architecture, and  Android Architecture Components were used
to implement it. The reactive pattern is used with the help of LiveData. A Repository serves as a single
point of entry for the data. Due to the nature of the app, the images aren't persisted in a database and
they are accessed through the folder that the user selected.

Although the app is simple and the whole implementation might seem like an overkill, the reason behind
it is that the app is robust, modular and scalable. If, for example, we want to add code to communicate
with the network or save data in a database, we can easily do it by changing the Repository. The presentation
layer (Activities, Fragments, ViewModels) doesn't care about the nature of the data or where they come from.

Glide was used for image loading. https://github.com/bumptech/glide
A 3rd party library was used for the folder picker. https://android-arsenal.com/details/1/5837
