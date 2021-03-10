<h1 align="center"> What is the most efficient compression algorithm out of Zstandard, ZIP, Slime and Zlib when dealing with finite Minecraft worlds?
 </h1> <br>

<p align="center">
    <a href="https://www.ibo.org/programmes/diploma-programme/curriculum/extended-essay/what-is-the-extended-essay/">
      <img alt="IB-Logo" title="IB-Logo" src="https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/International_Baccalaureate_Logo.svg/1200px-International_Baccalaureate_Logo.svg.png" width="200">
  </a>
</p>

<p align="center">
  My Computer Science Extended Essay for the IB Diploma
</p>

## Table of Contents

- [What is Extended Essay?](#what)
- [About](#about)
- [Why?](#why)
- [Contributing](#contributing)
- [Acknowledgments](#acknowledgments)

## What is Extended Essay? <a name="what"></a>
The IB defines the Extended Essay as ["an independent piece of research, culminating with a 4,000-word paper"]. 

As a student part of the IB program, I am required to write an Extended Essay, and I chose to do it about to Extended Essay.


["an independent piece of research, culminating with a 4,000-word paper"]: https://www.ibo.org/programmes/diploma-programme/curriculum/extended-essay/what-is-the-extended-essay/

## About
Cool, so what are you doing?

I am comparing different compression algorithms between them when dealing with compressing and uncompressing Minecraft worlds.

These algorithms include:

- [Zlib/gzip (DEFLATE)][1]
- [Zstandard (zstd)][2]
- [The Slime world format][3]

The measurments take into account the kilobytes reduced per second, meaning how much the file size is reduced from the original world size, and how long it takes to compress and load.


[1]: https://github.com/madler/zlib
[2]: https://github.com/facebook/zstd
[3]: https://hypixel.net/threads/dev-blog-5-storing-your-skyblock-island.2190753/

## Why? <a name="why"></a>
Great, why are you hosting it on GitHub?

Well GitHub is a great place to upload your code.

I mostly uploaded here because it was the best way to attach it to the essay, so if the IB wants to have access to the code, they can access it without a problem.

Also, who knows, but someone can maybe benefit from my code/improve it, that is why GitHub is such a great place.

## Contributing

Great, so how can I help?

To be honest, I don't really need any help, most of the code is already done and I am just hosting it here for ease of accesss.

However, if you wan't to make pull requests in order to improve the code, or fix any bug, go ahead. You are free of forking the code or modifying it however you want, that is the beauty of GitHub.

###### By the way, I know the code is messy. I will commit soon with a code clean-up, which (hopefully) will be easier to read.

## Acknowledgments

[Spigot-API][1]: Used to make the plugin to interact with the Minecraft Server.

[Java bindings for zstd (zstd-jni)][2]

[Minikloon][3] Developer of the slime-world format used in this research paper.

[Slime-World-Manager][4] Tools used to create, load and manage worlds with the slime-world format.


[1]:https://www.spigotmc.org/
[2]:https://github.com/luben/zstd-jni
[3]:https://twitter.com/Minikloon
[4]:https://github.com/Grinderwolf/Slime-World-Manager

<br>
<br>
<br>

[![forthebadge](https://forthebadge.com/images/badges/built-with-love.svg)](https://forthebadge.com) [![forthebadge](https://forthebadge.com/images/badges/not-a-bug-a-feature.svg)](https://forthebadge.com) [![forthebadge](https://forthebadge.com/images/badges/60-percent-of-the-time-works-every-time.svg)](https://forthebadge.com)

