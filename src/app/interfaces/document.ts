export interface Document {
  id: string;
  author: string;
  extension: string;
  title: string;
  creationDate: Date;
  file?: File;
}
