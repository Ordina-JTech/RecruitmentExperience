import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { EditDialog, EditField, FieldType } from 'src/app/definitions/edit-dialog';

@Component({
  selector: 'app-edit-dialog',
  templateUrl: './edit-dialog.component.html',
  styleUrls: ['./edit-dialog.component.scss']
})
export class EditDialogComponent implements OnInit {

  editForm: FormGroup;
  fieldNames: string[];
  fields: EditDialog;

  selectedFiles: any = {};

  constructor(private dialogRef: MatDialogRef<EditDialogComponent>,
              @Inject(MAT_DIALOG_DATA) private data: EditDialog) {}

  ngOnInit() {
    this.fields = this.data;
    this.fieldNames = Object.keys(this.data);

    const formControls = this.fieldNames.reduce((acc, key) => {
      const field = this.data[key];
      return {
        ...acc,
        [key]: new FormControl(this.getInitialValue(field)),
      };
    }, {});

    this.editForm = new FormGroup(formControls);
  }

  getInitialValue(field: EditField): any {
    if (field.initialValue !== undefined) {
      return field.initialValue;
    } else {
      switch (field.type) {
        case FieldType.Number: return 0;
        case FieldType.RichText:
        case FieldType.Text: return '';
        case FieldType.Select: return field.options[0].value;
        case FieldType.Date: return new Date();
      }
    }
  }

  getFormattedFieldValue(fieldName: string) {
    const {type} = this.fields[fieldName];

    switch (type) {
      case FieldType.Date: return new Date(this.editForm.value[fieldName]);
      default: return this.editForm.value[fieldName];
    }
  }

  handleSelectFile = (fieldName: string, event: Event) => {
    this.selectedFiles[fieldName] = (event.target as any).files[0];
  }

  submit = (e: Event) => {
    e.preventDefault();
    
    const result = this.fieldNames.reduce((acc: any, fieldName: string) => {
      return {
        ...acc,
        [fieldName]: acc[fieldName] ? acc[fieldName] : this.getFormattedFieldValue(fieldName), 
      }
    }, this.selectedFiles);

    this.dialogRef.close(result);
  }
}
