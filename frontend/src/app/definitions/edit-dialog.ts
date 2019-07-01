export enum FieldType {
  Date = 'datetime-local',
  Text = 'text',
  RichText = 'richtext',
  Number = 'number',
  Select = 'select',
  File = 'file',
}

export interface Option {
  name: number | string;
  value: any;
}

export interface EditField {
  type: FieldType;
  initialValue?: any;
  label: string;
  options?: Option[];
}

export interface EditDialog {
  [index: string]: EditField;
}
