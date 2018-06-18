import { Component, OnInit } from "@angular/core";
import { ArticleMenu } from "../../../response/article.response";
import { ArticleService } from "../../../services/article.service";
import { NzMessageService } from "ng-zorro-antd";
import { TagService } from "../../../services/tag.service";

@Component({
    selector: 'app-article-menu',
    templateUrl: './menu.component.html',
    providers: [
        ArticleService,
        TagService,
    ]
})
export class ArticleMenuComponent implements OnInit {

    menu = '';
    parent = '';
    parentId = 0;
    comment = '';
    isMenuVisible = false;
    childrenList = [];
    menuLoading = false;
    menuList = new Array<ArticleMenu>();
    mouseOnId = 0;

    constructor(
        private article: ArticleService,
        private msg: NzMessageService) { }

    ngOnInit() {
        this.initData();
    }

    private initData() {
        this.article.childrenMenu(0).then(res => {
            if (res.status === 0) {
                this.menuList = res.list;
            } else {
                this.msg.error(res.message);
            }
        });
    }

    save() {

        if ('' === this.menu) {
            this.msg.warning('填写栏目名称');
            return;
        }

        this.article.saveMenu(this.menu, this.parentId, this.comment).then(res => {
            if (res.status === 0) {
                this.msg.success(`文章栏目[${this.menu}]已保存`);
            } else {
                this.msg.error(res.message);
            }
        }).then(() => {
            this.initData();
        });
    }

    selectParent() {
        this.isMenuVisible = true;
        this.menuLoading = true;
        this.article.childrenMenu(0).then(res => {
            this.childrenList = [];
            this.menuLoading = false;
            if (res.status === 0) {
                this.childrenList = res.list;
            } else {
                this.msg.warning(res.message);
            }
        });
    }

    handleMenuCancel() {
        this.isMenuVisible = false;
    }

    handleMenuOK() {
        this.isMenuVisible = false;
    }

    cc(model: ArticleMenu) {
        this.parent = model.title;
        this.parentId = model.id;
        this.isMenuVisible = false;
    }

    ccc(id: number) {
        this.article.childrenMenu(id).then(res => {
            if (res.status === 0) {
                this.childrenList = res.list;
            } else {
                this.msg.warning(res.message);
            }
        });
    }

    childMenu(menu: ArticleMenu) {
        this.article.childrenMenu(menu.id).then(res => {
            this.childrenList = [];
            this.mouseOnId = 0;
            if (res.status === 0) {
                this.mouseOnId = menu.id;
                this.childrenList = res.list;
            } else {
                console.log(res)
            }
        });
    }

    ml() {
        this.childrenList = [];
        this.mouseOnId = 0;
    }

    delete(menu: ArticleMenu) {
        this.article.deleteMenu(menu.id).then(res => {
            if (res.status === 0) {
                this.msg.success('已删除。');
                this.initData();
            } else {
                this.msg.error(res.message);
            }
        });
    }
}
