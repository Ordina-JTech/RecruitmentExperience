export interface Document {
  uuid: string;
  author: string;
  extension: string;
  title: string;
  creationDate: Date;
  file?: File;
}
