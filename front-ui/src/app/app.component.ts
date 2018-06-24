import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'app';
  array = [1, 2, 3, 4];

  data = new Array(5).fill({}).map((i, index) => {
    return {
      href: 'http://ant.design',
      title: `ant design part ${index}`,
      avatar: 'https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png',
      description: 'Ant Design, a design language for background applications, is refined by Ant UED Team.',
      content: `We supply a series of design principles, practical patterns and high quality design resources\
(Sketch and Axure), to help people create their product prototypes beautifully and efficiently.`
    };
  });

  ngOnInit() {

  }

}
