export interface ArticleVi {
  id: number;
  title: string;
  summary: string;
  imageUrl: string;
  author: string;
  authorId: number;
  content: string;
  status: number;
  viewCount: number;
  agreeCount: number;
  shareCount: number;
  allowComment: boolean;
  ranking: number;
  rankingCount: number;
  createAt: Date;
  updateAt: Date;
  publishAt: Date;
}

export enum AticleStatus {
  draft = 0,
  public = 1,
  private = 2
}
